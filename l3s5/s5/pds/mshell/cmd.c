#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h> 
#include <ctype.h>
#include "sighandler.h"
#include <sys/types.h>
#include <sys/wait.h>
#include <errno.h> 
#include "jobs.h"	
#include "cmd.h"
struct job_t *  traiter_cmd(char **argv)
{
struct job_t *job = NULL;
if (argv[1] == NULL) {
   printf("%s command il y a pas de pid ou jid\n", argv[0]);
     return NULL;}
 if (isdigit((int) argv[1][0])) {
	pid_t pid = atoi(argv[1]);//atoi可以把字符串转换成整型
	if (!(job = jobs_getjobpid(pid))) {
	    printf("(%d): No such process\n", (int) pid);
	    return NULL;
	}
    }
    else if (argv[1][0] == '%') {
	int jid = atoi(&argv[1][1]);
	if (!(job = jobs_getjobjid(jid))) {
	    printf("%s: No such job\n", argv[1]);
	    return NULL;
	}
    }
    else {
	printf("%s: argument must be a PID or %%jobid\n", argv[0]);
	return NULL;
    }

    return job;

}
void do_bg(char **argv) 
{
    struct job_t *job =traiter_cmd(argv);
    if (!job)
      return;
    else{
     jobs_kill(job->jb_pid,SIGCONT);
     job->jb_state = BG;
    }
    return;
}
void do_fg(char **argv) 
{   struct job_t *job = traiter_cmd(argv);
    if (!job)
      return;
    else{
     jobs_kill(job->jb_pid,SIGCONT);//让进程继续运行
     job->jb_state = FG;
     sleep(1);
    }
    return;
}

void do_stop(char **argv) 
{
    struct job_t *job = traiter_cmd(argv);
    if (!job)
      return;
    else{
     jobs_kill(job->jb_pid,SIGSTOP);
     job->jb_state =ST;
    }
    return;
}
void do_kill(char **argv) 
{ 
    struct job_t *job = traiter_cmd(argv);
    if (!job)
      return;
    else{
     jobs_kill(job->jb_pid,SIGKILL);
    }
    return;
}

void do_exit()
{
   struct job_t *job = jobs_getstoppedjob();
   if(job!= NULL)
	printf("Ne peut pas quitter \n");
   else{
        exit(EXIT_SUCCESS);
   }
   return;
}

void do_jobs()
{
    jobs_listjobs();
    return;
}

