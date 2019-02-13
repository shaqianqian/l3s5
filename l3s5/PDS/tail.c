#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>




int main(int argc,char **argv)
{
    char *buffer=NULL;
    int fd, size,i=0,nb=0,nline=0;
    
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
    while (i<size) {
        
        if (buffer[i]=='\n') {
            i++;
            if(buffer[i]!='\n')
                nb++;
            if (i>=size)
                break;
            }
        else{
            if(buffer[++i]=='\0')
                nb++;
        }
    }
    i=0;
    
    while (argv[1][i+1]) {
        nline=nline*10+(argv[1][i+1]-'0');
        i++;
    }
    
    
    if (nline>nb) {
        perror("demande trop de ligne!!!!!\n");
        exit(EXIT_FAILURE);
    }

    i=size-1;
    while (nline!=0) {
        if (buffer[i]=='\n') {
            nline--;
        }
        i--;
        if (i<0) {
            i=0;
            break;
        }
    }

    fd=0;
    while (i<=size) {
        
         printf("%c",buffer[i++]);
        
    }
    close(fd);
    return size;

}
