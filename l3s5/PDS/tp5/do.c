#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/wait.h>
#include <unistd.h>
#include <assert.h>
#include "makeargv.h"
#include <signal.h>
int 
main(int argc, char** argv){

	int status,status2,i,ok,j=0,k,resultatAnd=1,resultatOr=0,resultatCC=0;
	pid_t pid[20];
    int ifAnd=0,ifOr=0,nbOption=1,ifCC=0,ifKK=0,ifContinue=1;
	int returnValeur[20];
	char **cmdargv;
	
	if(strcmp("--and",argv[1])==0){
		ifAnd=1;
	}
	
	else if(strcmp("--or",argv[1])==0){
		ifOr=1;
	}
	
	else{
		printf("Option erreur!\n");
		exit(EXIT_FAILURE);
	}
	
	if(strcmp("--cc",argv[2])==0){
		nbOption++;
		ifCC=1;
	}
	
	if(strcmp("--k",argv[3])==0){
		nbOption++;
		ifKK=1;
	}
	  
    
	for (i=nbOption+1; i<argc; i++) {

        status2 = makeargv(argv[i], " \t", &cmdargv);
		assert(status2>0);

		
		if(ifContinue){	

        switch(pid[i]=fork()){
				case -1: perror("Creation processus");
					 exit(EXIT_FAILURE);

				case 0:
					ok=execvp(*cmdargv,cmdargv);
                    printf("%s\n",cmdargv[1]);
					if(ok==-1){
						perror("Execution erreur");
						exit(EXIT_FAILURE);
					}
					exit(EXIT_SUCCESS);
					
			
				default:/*pere*/
					waitpid(pid[i],&status,0);
					if(WIFEXITED(status)){

						returnValeur[j++]=WEXITSTATUS(status);
						printf("[%s]Valeur de retour est %d\n",*cmdargv,returnValeur[j-1]);
							
						if(ifAnd&&returnValeur[j-1]==0&&ifCC){
							resultatCC=0;
							
							printf("*Il y a un program qui retourne 0 pour l'option --and  Donc on quitte. *\n");
							ifContinue=0;
	
                            
							if(!ifKK) exit(EXIT_SUCCESS);
							

						}
						if(ifOr&&returnValeur[j-1]==1&&ifCC){
							resultatCC=1;
							
							printf("*Il y a un program qui retourne 1 pour l'option --or  Donc on quitte. *\n");
							
							exit(EXIT_SUCCESS);
							ifContinue=0;
							if(!ifKK) exit(EXIT_SUCCESS);
						}
						
                        
					}
					else{	
                        printf("Valeur de retour erreur %s\n",argv[i]);
						exit(EXIT_FAILURE);
						
                        
					}
                
			}
			
            freeargv(cmdargv);
		}			
    }
    
    if(ifKK){
        printf("kill all \n");
		for (k=nbOption+1; j<=i; j++) {
            kill(pid[j],SIGINT);
        }
    }
   
    
	for(k=0;k<j;k++){
		if(ifAnd)   resultatAnd*=returnValeur[k];		
		else if(ifOr){
			if(resultatOr<returnValeur[k]) resultatOr=returnValeur[k];
		}
	}
	
	if(ifAnd) resultatCC=resultatAnd;
	else if(ifOr) resultatCC=resultatOr;
	printf("*Le résultat est %d       *\n",resultatCC);
	exit(EXIT_SUCCESS);
	/*fin de program*/
}
