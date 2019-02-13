#include <stdio.h>
#include <stdlib.h>
#include <sys/wait.h>
#include <sys/types.h>
#include <unistd.h>

int main(void){
        int i,j,status;	
	pid_t pid[10];
	
	for(i=0;i<10;i++){
		status = fork();
		if(status!=0){
			printf("On a creé No.%d processus, son pid est %d\n",i+1,status);
			if(i==9) printf("\n");		
		}
		switch(status){
			case -1:
				perror("erreur fork\n");
				exit(EXIT_FAILURE);	
			case 0 : /*fils*/
				j=0;
   				while(j<5000000)
      				    j++;
   			        printf("Processus[%d] compte 5000000\n",getpid());

				j=0;
				while(j<5000000)
     				    j++;	
				printf("Processus[%d] est terminé\n",getpid());			
				exit(EXIT_SUCCESS);
		}
	}
	
    	for (i=0; i<10; i++) { 
		pid[i]=wait(&status);
	}
	
	printf("\nL'ordre des processus :\n");
   	for (i=0; i<10; i++) {
		printf("Processus[%d] arrive, son place est %d\n",pid[i],i+1);
   	 }
	exit(EXIT_SUCCESS);
}																															

