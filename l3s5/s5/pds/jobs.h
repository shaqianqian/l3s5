
#include <sys/types.h>

enum state {BG,FG,ST,EMP};
#define MAXLINE    1024   /* max line size */
#define MAXARGS     128   /* max args on a command line */
#define MAXJOBS      16   /* max jobs at any point in time */
#define MAXJID    1<<16   /* max job ID */

struct job_t {                 /* The job struct */
    pid_t pid;              /* job PID */
    int jid;                /* job ID [1, 2, ...] */
    int state;              /*  BG, FG, ST ,EMP*/
    char cmdline[MAXLINE];  /* command line */
};

extern void clearjob(struct job_t *job);   

extern void initjobs();

extern int maxjid();    

extern int addjob(pid_t pid, int state, char *cmdline); 

extern int deletejob(pid_t pid); 

/* fgpid - Return PID of current foreground job, 0 if no such job */
extern pid_t fgpid();

extern struct job_t * getjobpid(pid_t pid);

extern struct job_t * getjobjid(int jid); 

extern int pid2jid(pid_t pid); 

/*getstoppedjob - Return a stopped job if any  */
extern struct job_t * getstoppedjob();

/* listjobs - Print the job list */
extern void listjobs();

