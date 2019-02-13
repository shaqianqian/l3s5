/*TP4 LIN Renjie & YANG Lu*/
#include <stdio.h>
#include <stdlib.h>
#include <sys/wait.h>
#include <sys/types.h>
#include <unistd.h>

typedef void (*func_t) (void*);

void forkfork(func_t f,void *arg){
    pid_t pid;
    pid=fork();
   
        if (pid==-1) {
            perror("erro fork");
            exit(EXIT_FAILURE);
    
        }
        else if(pid==0){
            if ((pid = fork()) < 0)
                perror("fork error");
            else if (pid > 0)
                exit(0);
                sleep(2);
                f(arg);
                printf("[%d]je suis = %d\n", getpid(),getpid());
                printf("[%d]le deuxième fils = %d\n[%d]mon pere est = %d\n", getpid(),getpid(),getpid(),getppid());
            exit(0);
        }
        else{
            printf("[%d]je suis le pere\n[%d]mon fils est = %d\n", getppid(),getppid(),getpid());
            exit(0);
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
