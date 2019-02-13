
#include <stdio.h>
typedef void handler_t(int);

extern int sighandler_wrapper(int signum, handler_t *handler);
extern void sigchld_handler(int sig);
extern void sigint_handler(int sig);
extern void sigtstp_handler(int sig);
extern void sigquit_handler(int sig);



