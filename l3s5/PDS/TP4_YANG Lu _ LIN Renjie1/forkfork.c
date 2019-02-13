#include <stdio.h>
#include <stdlib.h>
#include <sys/wait.h>
#include <sys/types.h>
#include <unistd.h>

typedef void (*func_t) (void*);

void forkfork(func_t f,void *arg){
    pid_t pid;
    int i=0;
    pid=fork();
    for(i=0;i<2;i++){
        if (pid==-1) {
            perror("erro fork");
            exit(EXIT_FAILURE);
    
        }
        else if(pid==0){
            if (i==1) {
                sleep(2);
                f(arg);
                printf("[%d]je suis = %d\n", getpid(),getpid());
                printf("[%d]le deuxième fils = %d\n[%d]mon pere est = %d\n", getpid(),getpid(),getpid(),getppid());
            }
            
        }
        else{
            printf("[%d]je suis le pere\n[%d]mon fils est = %d\n", getppid(),getppid(),getpid());
            exit(0);
        }
    }
}

void test(){
    
    fprintf(stderr, "Fonction executee par le processus %d\n",
            getpid()) ;
}


int main(int argc, char *argv[]){
    forkfork(test,"");
    fprintf(stderr, "terminaison de main()\n");
    
    exit(EXIT_SUCCESS);
}
