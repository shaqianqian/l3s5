#ifndef _MAKEARGV_H_
#define _MAKEARGV_H_

extern int makeargv(const char *s, const char *delimiters, char ***argvp);

extern void freeargv(char **argv);

#endif
