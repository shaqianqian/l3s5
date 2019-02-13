#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <assert.h>
#include <sys/types.h>
#include <sys/wait.h>

#define STO 1
#define STI 0

int main(int argc, char* argv[]){
  int fds[2];
  int status,i;
	
  status = pipe(fds);
  assert(status != -1);
  
  for (i = 0; i < argc - 1; i++){ /* les n-1 fils */
    status = pipe(fds);

    switch(fork())
      {
      case -1:
	unix_error("Error fork");

      case 0:
	status = dup2(fds[STO],STO);
	if (status < 0)
	  unix_error("Error dup2");
	close(fds[0]);
	execvp(cmds[i][0],cmds[i]);
	exit(EXIT_FAILURE);

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
      execvp(cmds[i][0],cmds[i]);
      exit(EXIT_FAILURE);

    default:;
    }

  close(fds[STI]);
  close(fds[STO]);
  for(i = 0; i < argc -1; i++) /*on attend tous les fils*/
    wait(NULL);

  exit(EXIT_SUCCESS);
}
