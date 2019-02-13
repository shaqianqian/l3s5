/* ------------------------------
   $Id: pipe.c,v 1.2 2005/03/29 09:46:52 marquet Exp $
   ------------------------------------------------------------

   mshell - a job manager
   
*/

#include<stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/wait.h>
#include "pipe.h"
#include "common.h"
#include "unistd.h"
#include "jobs.h"
#include "cmd.h"
#include <string.h>

static char cmdline[1024];

void build_cmdline(char *cmds[MAXCMDS][MAXARGS],int nbcmd,int bg) {
  int i;
  int j;

  strcpy(cmdline,"");
  j = 0;
  for (i = 0 ; i < nbcmd ; i++) {
    while (cmds[i][j]) {
      strcat(cmdline,cmds[i][j]);
      strcat(cmdline," ");
      j++;
    }
    (i != nbcmd - 1) ? strcat(cmdline,"| ") : strcat(cmdline,"");
    j = 0;
  }
  (bg) ? strcat(cmdline," &") : 0;
}


void do_pipe(char *cmds[MAXCMDS][MAXARGS], int nbcmd, int bg) {

  int fds[2];
  int status,i;
  pid_t pid;
  sigset_t mask;
  if (verbose)
    printf("do_pipe : entering\n");


  if (sigemptyset(&mask) < 0)
    unix_error("sigemptyset error");
  if (sigaddset(&mask, SIGCHLD))
    unix_error("sigaddset error");
  if (sigaddset(&mask, SIGINT))
    unix_error("sigaddset error");
  if (sigaddset(&mask, SIGTSTP))
    unix_error("sigaddset error");
  if (sigprocmask(SIG_BLOCK, &mask, NULL) < 0)
    unix_error("sigprocmask error");

  switch (pid = fork()){

  case -1:
    unix_error("Error fork");
  case 0:
    if(setpgid(0,0) < 0)
      unix_error("setpgid error");
	

    for (i = 0; i < nbcmd - 1; i++){ /* les n-2 fils suivant*/
      status = pipe(fds);

      if (status < 0)
	unix_error("Error pipe");

      switch(pid = fork())
	{
	case -1:
	  unix_error("Error fork");

	case 0:
	  sigprocmask(SIG_UNBLOCK, &mask, NULL);
	  status = dup2(fds[1],STDOUT_FILENO);
	  if (status == -1)
	    unix_error("Error dup2");
	  close(fds[0]);
	  if (execvp(cmds[i][0],cmds[i])){
	    printf("%s: Command not found\n", cmds[i][0]);
	    exit(EXIT_FAILURE);
	  }

	default:
	  dup2(fds[0],STDIN_FILENO);
	}
    }
    switch(pid =fork())/* le n-ième fils */
      {
      case -1:
	unix_error("Error fork");

      case 0:
	sigprocmask(SIG_UNBLOCK, &mask, NULL);
	if (execvp(cmds[i][0],cmds[i])){
	  printf("%s: Command not found\n", cmds[i][0]);
	  exit(EXIT_FAILURE);
	}
      default:
	sigprocmask(SIG_UNBLOCK, &mask, NULL);
	close(fds[0]);
	close(fds[1]);
	exit(EXIT_SUCCESS);
      
     
      }
    
  default :
    build_cmdline(cmds,nbcmd,bg);
    jobs_addjob(pid,(bg == 1 ? BG : FG), cmdline);
    sigprocmask(SIG_UNBLOCK, &mask, NULL);
    if(!bg){
      waitfg(pid);
    }
    else
      printf("[%d] (%d) %s\n", jobs_pid2jid(pid), (int) pid, cmdline);
  }


  if (verbose)
    printf("do_pipe : exiting\n");

  fflush(stdout);
}

