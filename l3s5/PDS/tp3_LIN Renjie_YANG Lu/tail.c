#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>




int main(int argc,char **argv)
{
    char *buffer=NULL;
    int fd,cpt, size,i=0,nb=0,nline=0;
    
    buffer=(char*)malloc(sizeof(char));
    
    fd = open(*(argv+2), O_RDONLY);
    
    while(1){
        size=read(fd, buffer+i,2000);
        if (size<2000) {
            break;
        }
        else{
            i++;
            buffer=(char*)realloc(buffer+i*2000,(i+1)*2000*sizeof(char));
        }
    }
    size=size+i*2000;
    
    
    i=0;
    
    while (argv[1][i+1]) {
        nline=nline*10+(argv[1][i+1]-'0');
        i++;
    }
    
    i=0;
    while (i<size) {
        if (buffer[size-i]=='\n') {
            nb++;
            if (nb>nline){
                cpt=size-i+1;
                break;
            }
        }
        i++;
    }
    
    if (nb==nline&&i==size) {
        cpt=0;
    }
    
    if (nline>nb) {
        perror("demande trop de ligne!!!!!\n");
        exit(EXIT_FAILURE);
    }
    while (buffer[cpt]) {
        printf("%c",buffer[cpt]);
        cpt++;
    }

      close(fd);
    return size;

}
