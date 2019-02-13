/* ------------------------------
   $Id: pipe.c,v 1.2 2005/03/29 09:46:52 marquet Exp $
   ------------------------------------------------------------

   mshell - a job manager
   
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <signal.h>
#include <sys/wait.h>
#include <sys/types.h>
#include <unistd.h>
#include <assert.h>
#include "pipe.h"
#include "jobs.h"
#include "cmd.h"

void do_pipe(char *cmds[MAXCMDS][MAXARGS], int nbcmd, int bg) {

  int i;
  int fds[MAXCMDS][2];
  pid_t pid[100];

 
  

  for(i=0;i<nbcmd;i++){
    if(i!=(nbcmd-1))    
     pipe(fds[i]);
  
    switch(pid[i]=fork()){
      case -1:perror("erreur fork\n");
              exit(EXIT_FAILURE);
      case 0: if(i==0){
                 close(fds[i][0]);
                 dup2(fds[i][1],STDOUT_FILENO);

              }else if(i==(nbcmd-1)){
                 close(fds[i-1][1]);
                 dup2(fds[i-1][0],STDIN_FILENO);
              }else{
	      close(fds[i-1][1]);
              dup2(fds[i-1][0],STDIN_FILENO);
              close(fds[i][0]);
              dup2(fds[i][1],STDOUT_FILENO);
		}
            if (setpgid(0, 0) < 0)
                unix_error("setpgid error");
              execvp(cmds[i][0],cmds[i]);
              perror("erreur d'execution");
         
      default: 
 	jobs_addjob(pid[i], (bg == 1 ? BG : FG), *cmds[i]);
     }
    } 

   for(i=0;i<nbcmd-1;i++){
     close(fds[i][0]);
     close(fds[i][1]);
  }

    
      for(i=0;i<nbcmd;i++){
          if (!bg)
        waitfg(pid[i]);
      }

       
    


  return;
}


