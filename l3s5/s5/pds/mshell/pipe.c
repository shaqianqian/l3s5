
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "sighandler.h"
#include <sys/wait.h>
#include <sys/types.h>
#include <unistd.h>
#include <assert.h>
#include "pipe.h"
#include "jobs.h"
#include "cmd.h"

void do_pipe(char *cmds[MAXCMDS][MAXARGS], int nbcmd, int bg) {
    int i;
    int fd[2];
    pid_t pid;
    
    
    pipe(fd);
    if(pipe(fd)<0)
    {perror("erreur fork\n");}
    switch(pid=fork()){
        case -1:perror("erreur fork\n");
            exit(EXIT_FAILURE);
        case 0: if(i==0){
            close(fd[0]);
            dup2(fd[1],STDOUT_FILENO);
            close(fd[1]);
            execvp(cmds[0][0],cmds[0]);
          
            break;
      }
    switch(pid=fork()){
         case -1:perror("erreur fork\n");
                    exit(EXIT_FAILURE);
         case 0: if(i==0){
             close(fd[1]);
             dup2(fd[0],STDOUT_FILENO);
             close(fd[0]);
             execvp(cmds[1][0],cmds[1]);
                    
             break;
                }
                    
           close(fd[0]);
            close(fd[1]);
            wait(NULL);
        wait(NULL);}
    
   
    return;
}
}
