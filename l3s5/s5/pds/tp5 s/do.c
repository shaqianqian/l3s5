
#include<stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/wait.h>
#include <unistd.h>
#include <assert.h>
#include <signal.h>
#include "makeargv.h"
int main(int argc,char **argv ) {
    int a=0;
    int o=0;
    int c=0;
    int k=0;
    int r=0;
    int num_command=1;
    int status;
    char **address_com;
    pid_t pid[20];
    int j=0;
    	
	if(strcmp("-a",argv[1])==0){
		num_command=1;
		a=1;
	}
		
	if(strcmp("-o",argv[1])==0){
		o=1; num_command=1;
	} 
        if(strcmp("-c",argv[2])==0){
                c=2; num_command=2;
        }
         if(strcmp("-k",argv[3])==0){
                k=1; num_command=1;;;
       }
   for(int i=1;i<argc;i++){

    makeargv(argv[i], " \t", &address_com);
    switch(pid[i]=fork()){
        case -1: perror("Creation processus");
            exit(EXIT_FAILURE);

        case 0:
            execvp(address_com[0],address_com);
        default:
         
	  freeargv(address_com);}
    for (int i = 1; i < argc; i++ ) {
        pid[i]= wait(&status);

   if(a&&!o){r=1;}
    else {r=0;}
        if(c){if(k) { kill(pid[1],SIGKILL); }

            printf("%d\n",r);
            return r; }

        printf("%d\n",r);
        return r; }

}


}
