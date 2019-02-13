//
//  signal.h
//  
//
//  Created by 沙倩倩 on 16/11/1.
//
//

#ifndef signal_h
#define signal_h

#include <stdio.h>
typedef void handler_t(int);

extern int signal_wrapper(int signum, handler_t *handler);
extern void sigchld_handler(int sig);
extern void sigint_handler(int sig);
extern void sigtstp_handler(int sig);
extern void sigquit_handler(int sig);


#endif /* signal_h */
