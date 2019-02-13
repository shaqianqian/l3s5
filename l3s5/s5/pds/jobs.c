#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <ctype.h>
#include <signal.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <errno.h>
#include "jobs.h"

static struct job_t jobs[MAXJOBS];
int nextjid = 1;   
int verbose = 0;  

void clearjob(struct job_t *job){
    job->pid = 0;
    job->jid = 0;
    job->state = EMP;
    job->cmdline[0] = '\0';
}
void initjobs() {
    int i;
    for (i = 0; i < MAXJOBS; i++)
	clearjob(&jobs[i]);
}

int maxjid() 
{
    int i, max=0;
     for (i = 0; i < MAXJOBS; i++)
	if (jobs[i].jid > max)
	    max = jobs[i].jid;
    return max;
}

int addjob(pid_t pid, int state, char *cmdline) 
{
    int i;
     if (pid < 1)
	return 0;
    for (i = 0; i < MAXJOBS; i++) {
	if (jobs[i].pid == 0) {
	    jobs[i].pid = pid;
	    jobs[i].state = state;
	    jobs[i].jid = nextjid++;
	    if (nextjid > MAXJOBS)
		nextjid = 1;
	    strcpy(jobs[i].cmdline, cmdline);
  	    if(verbose){
	        printf("Added job [%d] %d %s\n", jobs[i].jid, jobs[i].pid, jobs[i].cmdline);
            }
            return 1;
	}}
    
    printf("Tried to create too many jobs\n");
    return 0;
}

int deletejob( pid_t pid) 
{
    int i; if (pid < 1)return 0;
for(i = 0; i < MAXJOBS; i++){
  	if (jobs[i].pid == pid) {
	    clearjob(&jobs[i]);
	    nextjid = maxjid(jobs)+1;
	    return 1;
	}
    }
    return 0;
}


pid_t fgpid() {
    int i;for (i = 0; i < MAXJOBS; i++)
if (jobs[i].state == FG)
	    return jobs[i].pid;
    return 0;
}


struct job_t * getjobpid( pid_t pid) {
    int i;

    if (pid < 1)
	return NULL;
    for (i = 0; i < MAXJOBS; i++)
	if (jobs[i].pid == pid)
	    return &jobs[i];
    return NULL;
}
  
  struct job_t * getjobjid( int jid) 
{
    int i;

    if (jid < 1)
	return NULL;
    for (i = 0; i < MAXJOBS; i++)
	if (jobs[i].jid == jid)
	    return &jobs[i];
    return NULL;
}

int pid2jid(pid_t pid) 
{
    int i;

    if (pid < 1)
	return 0;
    for (i = 0; i < MAXJOBS; i++)
	if (jobs[i].pid == pid) {
            return jobs[i].jid;
        }
    return 0;
}

void listjobs() 
{

    int i;
    
    for (i = 0; i < MAXJOBS; i++) {
	if (jobs[i].pid != 0) {
	    printf("[%d] (%d) ", jobs[i].jid, jobs[i].pid);
	    switch (jobs[i].state) {
		case BG: 
		    printf("Running ");
		    break;
		case FG: 
		    printf("Foreground ");
		    break;
		case ST: 
		    printf("Stopped ");
		    break;
	    default:
		    printf("listjobs: Internal error: job[%d].state=%d ", 
			   i, jobs[i].state);
	    }
	    printf("%s", jobs[i].cmdline);
	}
    }
}
struct job_t * getstoppedjob() {

    int i;

    for (i = 0; i < MAXJOBS; i++)
      if (jobs[i].state == ST)
	return &jobs[i];
    return NULL;
}

