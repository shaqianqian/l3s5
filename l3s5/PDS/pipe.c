/* ------------------------------
   $Id: pipe.c,v 1.2 2005/03/29 09:46:52 marquet Exp $
   ------------------------------------------------------------

   mshell - a job manager
   
   YANG Lu & LIN Renjie
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <signal.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>
#include "pipe.h"
#include "jobs.h"

void do_pipe(char *cmds[MAXCMDS][MAXARGS], int nbcmd, int bg) 
{
  int fds[2];
  pid_t pid1;
  pid_t pid2;
  int i ;
  int status;
  pipe(fds);
  for (i =0; i<nbcmd-1;i++){
    pipe(fds);
    switch(pid1 = fork()){
            case -1:
           	 printf(" erreur lors de la création du tube \n");
      		 return;
   
   	    case 0:
                  close(fds[0]);
                  dup2(fds[1],STDOUT_FILENO);
      		  execvp(*cmds[i], cmds[i]);
      		  exit(EXIT_SUCCESS);
    
    	    default:
     		  close(fds[1]);
      		  dup2(fds[0],STDIN_FILENO);
      		  close(fds[0]);
   }
  }
  pid2=fork();
  if (pid2==-1) {
    printf(" erreur lors de la création du tube \n");
    return;
  }
  else if (pid2==0) {
    close(fds[0]);
    dup2(fds[1],STDOUT_FILENO);
    execvp(*cmds[nbcmd-1], cmds[nbcmd-1]);
    exit(EXIT_SUCCESS);
  }
  printf("%d\t%d\n",getpid(),getppid()); 
  for(i=0; i<nbcmd ; i++)
    wait(&status);
  return;
}
  
