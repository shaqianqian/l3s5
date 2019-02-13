/* ------------------------------
   $Id: pipe.c,v 1.2 2005/03/29 09:46:52 marquet Exp $
   ------------------------------------------------------------

   mshell - a job manager
   
*/

#include<stdio.h>
#include <stdlib.h>
#include <signal.h>
#include "pipe.h"
#include "common.h"
#include "unistd.h"
#include "jobs.h"
#include "cmd.h"

#define STI 0
#define STO 1

void do_pipe(char *cmds[MAXCMDS][MAXARGS], int nbcmd, int bg) {

  int fds[2];
  int status,i;
  pid_t pid;
  sigset_t mask;       /* signal mask */


  /* 
   * This is a little tricky. Block SIGCHLD, SIGINT, and SIGTSTP
   * signals until we can add the job to the job list. This
   * eliminates some nasty races between adding a job to the job
   * list and the arrival of SIGCHLD, SIGINT, and SIGTSTP signals.  
   */
      
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


  status = pipe(fds);
  if (status < 0)
    unix_error("Error pipe");
  
  switch((pid = fork()))
      {
      case -1:
	unix_error("Error fork");

      case 0:
	/* Child unblocks signals */
	sigprocmask(SIG_UNBLOCK, &mask, NULL);
	
	/* Each new job must get a new process group ID 
	   so that the kernel doesn't send ctrl-c and ctrl-z
	   signals to all of the shell's jobs */
	if (setpgid(getpid(), 0) < 0) 
	  unix_error("setpgid error"); 

	status = dup2(fds[STO],STO);
	if (status < 0)
	  unix_error("Error dup2");
	close(fds[0]);
	if (execvp(cmds[0][0],cmds[0])){
	  printf("%s: Command not found\n", cmds[0][0]);
	  exit(EXIT_FAILURE);
	}

      default:
	dup2(fds[STI],STI);
      }

  for (i = 1; i < nbcmd - 2; i++){ /* les n-2 fils suivant*/
    status = pipe(fds);

    if (status < 0)
      unix_error("Error pipe");

    switch(fork())
      {
      case -1:
	unix_error("Error fork");

      case 0:
	status = dup2(fds[STO],STO);
	if (status < 0)
	  unix_error("Error dup2");
	close(fds[0]);

	/* Child unblocks signals */
	sigprocmask(SIG_UNBLOCK, &mask, NULL);

	/* Each new job must get a new process group ID 
	   so that the kernel doesn't send ctrl-c and ctrl-z
	   signals to all of the shell's jobs */
	if (setpgid(getpid(), pid) < 0) 
	  unix_error("setpgid error");

	if (execvp(cmds[i][0],cmds[i])){
	  printf("%s: Command not found\n", cmds[i][0]);
	  exit(EXIT_FAILURE);
	}

      default:;
      }
    dup2(fds[STI],STI);
  }

  switch(fork())/* le n-ième fils */
    {
    case -1:
      unix_error("Error fork");

    case 0:
      status = dup2(fds[STO],STO);
      if (status < 0)
	unix_error("Error dup2");
      close(fds[0]);

      /* Child unblocks signals */
      sigprocmask(SIG_UNBLOCK, &mask, NULL);

      /* Each new job must get a new process group ID 
	 so that the kernel doesn't send ctrl-c and ctrl-z
	 signals to all of the shell's jobs */
      if (setpgid(getpid(), pid) < 0) 
	unix_error("setpgid error");

      if (execvp(cmds[i][0],cmds[i])){
	printf("%s: Command not found\n", cmds[i][0]);
	exit(EXIT_FAILURE);
      }

    default:;
    }

  close(fds[STI]);
  close(fds[STO]);


  /* Parent adds the job, and then unblocks signals so that
     the signals handlers can run again */
  jobs_addjob(pid, (bg == 1 ? BG : FG), cmds[0][0]);
  sigprocmask(SIG_UNBLOCK, &mask, NULL);

  if(!bg)
    waitfg(pid);
  else
    printf("coucou\n");

  return;
}
