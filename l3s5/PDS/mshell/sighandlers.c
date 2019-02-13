/* ------------------------------
   $Id: sighandlers.c,v 1.1 2005/03/17 13:00:46 marquet Exp $
   ------------------------------------------------------------

   mshell - a job manager
   
*/

#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <signal.h>
#include <sys/wait.h>
#include <errno.h>


#include "jobs.h"
#include "common.h"
#include "sighandlers.h"


/*
 * Signal - wrapper for the sigaction function
 */
int
signal_wrapper(int signum, handler_t *handler) 
{
    struct sigaction action;
    action.sa_handler = handler;
    sigemptyset(&action.sa_mask); /*block sigs of type being handled*/
    action.sa_flags = SA_RESTART; 
    if(sigaction(signum,&action,NULL)<0)
      unix_error("error de traitement");
    return 0;
}


/* 
 * sigchld_handler - The kernel sends a SIGCHLD to the shell whenever
 *     a child job terminates (becomes a zombie), or stops because it
 *     received a SIGSTOP or SIGTSTP signal. The handler reaps all
 *     available zombie children
 */
void
sigchld_handler(int sig) 
{
    pid_t pid;
    int status;
    if (verbose)
	printf("sigchld_handler: entering\n");
    while((pid = waitpid(-1,&status,WNOHANG | WUNTRACED))){  
      if(WIFSTOPPED(status)){
         jobs_getjobpid(pid)->jb_state=ST;
         printf("Job [%d] (%d) stopped by signal %d\n", 
	     jobs_pid2jid(pid), pid, WSTOPSIG(status)); /*afficher le signal qui terminer le job*/
         jobs_listjobs();
         printf("\n");
         break;
    }
      else if(WIFSIGNALED(status)){
         if(WTERMSIG(status)==2)
	   printf("Job [%d] (%d) terminated by signal %d\n", 
	       jobs_pid2jid(pid), pid, WTERMSIG(status));
         jobs_deletejob(pid);
         break;
    }
      else if(WIFEXITED(status)){
         jobs_deletejob(pid);
         break;
    }
 }
    if (verbose)
	printf("sigchld_handler: exiting\n");
    return;
}

/* 
 * sigint_handler - The kernel sends a SIGINT to the shell whenver the
 *    user types ctrl-c at the keyboard.  Catch it and send it along
 *    to the foreground job.  
 */
void
sigint_handler(int sig) 
{   
    pid_t pid;
    pid= jobs_fgpid();
    if (verbose)
       printf("sigint_handler: entering\n");
    if(pid>0)
    {
      if (kill(-pid,SIGINT)<0)
          unix_error("kill(sigint)error");
      if (verbose)
	  printf("sigint_handler: Job(%d)killed\n",(int)pid);
    }
    if (verbose)
       printf("sigint_handler: exiting\n");
    
    return;
}

/*
 * sigtstp_handler - The kernel sends a SIGTSTP to the shell whenever
 *     the user types ctrl-z at the keyboard. Catch it and suspend the
 *     foreground job by sending it a SIGTSTP.  
 */
void
sigtstp_handler(int sig) 
{
    pid_t pid;
    pid= jobs_fgpid();
    if (verbose)
       printf("sigint_handler: entering\n");
    if(pid>0)
    {
      if (kill(-pid,SIGTSTP)<0)
          unix_error("kill(sigstp)error");
      if (verbose)
	  printf("sigint_handler: Job(%d)stopped\n",(int)pid);
    }
    if (verbose)
       printf("sigint_handler: exiting\n");
    
    return;
}
