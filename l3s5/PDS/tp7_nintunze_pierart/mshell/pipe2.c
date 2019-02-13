/* ------------------------------
   $Id: pipe.c,v 1.2 2005/03/29 09:46:52 marquet Exp $
   ------------------------------------------------------------

   mshell - a job manager
   
*/

#include<stdio.h>
#include <stdlib.h>
#include "pipe.h"
#include "common.h"
#include "unistd.h"

#define STI 0
#define STO 1

void do_pipe(char *cmds[MAXCMDS][MAXARGS], int nbcmd, int bg) {

  int fds[2];
  int status,i;
  pid_t pid;
  
  status = pipe(fds);
  if (status < 0)
    unix_error("Error pipe");
  
  switch((pid = fork()))
      {
      case -1:
	unix_error("Error fork");

      case 0:
	status = dup2(fds[STO],STO);
	if (status < 0)
	  unix_error("Error dup2");
	close(fds[0]);
	if (execvp(cmds[0][0],cmds[0])){
	  printf("%s: Command not found\n", cmds[0][0]);
	  exit(EXIT_FAILURE);
	}

      default:
	dup2(fds[STI],STI);
      }

  for (i = 1; i < nbcmd - 2; i++){ /* les n-2 fils suivant*/
    status = pipe(fds);

    if (status < 0)
      unix_error("Error pipe");

    switch(fork())
      {
      case -1:
	unix_error("Error fork");

      case 0:
	status = dup2(fds[STO],STO);
	if (status < 0)
	  unix_error("Error dup2");
	close(fds[0]);
	if (execvp(cmds[i][0],cmds[i])){
	  printf("%s: Command not found\n", cmds[i][0]);
	  exit(EXIT_FAILURE);
	}

      default:;
      }
    dup2(fds[STI],STI);
  }

  switch(fork())/* le n-ième fils */
    {
    case -1:
      unix_error("Error fork");

    case 0:
      status = dup2(fds[STO],STO);
      if (status < 0)
	unix_error("Error dup2");
      close(fds[0]);
     if (execvp(cmds[i][0],cmds[i])){
	  printf("%s: Command not found\n", cmds[i][0]);
	  exit(EXIT_FAILURE);
	}

    default:;
    }

  close(fds[STI]);
  close(fds[STO]);

  /* if(!bg) */
  /*   waitfg(); */

  return;
}
