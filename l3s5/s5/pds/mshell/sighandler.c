
#include "sighandler.h"
#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include "sighandler.h"
#include <sys/wait.h>
#include <errno.h>
#include "jobs.h"


int sighandler_wrapper(int signum, handler_t *handler)
{
    struct sigaction action;
    action.sa_handler = handler;
    sigemptyset(&action.sa_mask); /*block sigs of type being handled*/
    action.sa_flags = SA_RESTART;
    return 0;
}


void sigint_handler(int sig)// control+C
{
    pid_t pid = jobs_fgpid();
    int jid = jobs_pid2jid(pid);
    if (pid != 0)
    {
        printf("Job [%d] (%d) terminated by signal %d\n", jid, pid, sig);
        jobs_deletejob(pid);
    }
    return;
}

void sigtstp_handler(int sig)// control+z
{
    int pid = jobs_fgpid();
    int jid = jobs_pid2jid(pid);
    
    if (pid != 0) {
        
        printf("Job [%d] (%d) Stopped by signal %d\n", jid, pid, sig);
        jobs_getjobpid(pid)->jb_state = ST;
        kill(-pid, SIGTSTP);
    }
    return;
}

void sigchld_handler(int sig)
{
    int status;
    pid_t pid;
    
    // Waiting for handling all of the child processes according to their status
    while ((pid = waitpid(fgpid(), &status, WNOHANG|WUNTRACED)) > 0) {
        if (WIFSTOPPED(status)){
            jobs_getjobpid(pid)->jb_state = ST;
            printf("[%d] Stopped %d\n", jobs_pid2jid(pid), WSTOPSIG(status));
        }
        else if (WIFSIGNALED(status)){
            printf("Job [%d] (%d) terminated by signal %d\n", jobs_pid2jid(pid), pid, WTERMSIG(status));
            jobs_deletejob(pid);
        }
        else if (WIFEXITED(status)){
            jobs_deletejob(pid);
        }
    }
    
   
    return;
}



