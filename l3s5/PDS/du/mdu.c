/*LIN_Renjie & YANG_Lu*/

#include <stdio.h>
#include <dirent.h>
#include <unistd.h>
#include <string.h>
#include <limits.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/stat.h>

static int opt_apparent_size=0;
static int opt_follow_links=0;

static int LesNoeuds[200];
static int nbChiffre=0;
/*LesNoeuds est une variable qui peut représenter les fichiers on a déjà parcouru.*/
/*nbChiffre est le nombre de chiffres dans le 'pathname'*/

int valid_name(const char*name){
	if(!strcmp(name,"."))
		return 0;
	else if (!strcmp(name,".."))
		return 0;
	else return 1;
}

int findChiffre(int *des,int s,int n){
	int i=0;
	int found=0;
	while(i<n+1){
        if(*(des+(i++))==s)
            found=1;
    }
	return found;
}
/*pour éviter de calculer un même fichier( au niveau du noeud ) plusieur fois*/
/*s'il y a un lien symbolique qui indiquer fichier X et X est calculé avant*/
/*on va passer ce fichier et ne calcule rien (0) */

int du_file(const char*pathname){
	
	struct stat st;
	struct dirent *dp;
	DIR *dirp;
	int size=0;
	int status;
    char buf[PATH_MAX+1];
	char* copie;
    size_t len;
    
    status = lstat(pathname,&st);
	copie=(char*)malloc(sizeof(char)*200);
    
	if(status){
		perror("apple de stat");
		return 0;
	}
    
    
	if (S_ISREG(st.st_mode)){
		if(!opt_apparent_size)
			size=st.st_blocks/2;
		else size=st.st_size;
	}
    
	else if(S_ISLNK(st.st_mode)){
		if(opt_follow_links){
			if(!opt_apparent_size)
				size=st.st_blocks/2;
			else size=st.st_size;
		}
		else{
            if((len = readlink(pathname,buf,PATH_MAX)) != -1)
                buf[len]='\0';
            else
                perror("erreur reading symlink\n");
            if(valid_name(buf))
                size = du_file(buf);
        }

	}
	
	else if(S_ISDIR(st.st_mode)){
            if((dirp=opendir(pathname))==NULL){
			perror("couldn't open it");
			return 0;
		}
        
		while((dp=readdir(dirp))){
			
			snprintf(copie,PATH_MAX,"%s/%s",pathname,dp->d_name);
            
			if(valid_name(dp->d_name))
                size += du_file(copie);
            printf("%s  ",copie);
            printf("taille:%d\n",size);
        }
        closedir(dirp);
	}
	
    if(!findChiffre(LesNoeuds,st.st_ino,nbChiffre)){
		*(LesNoeuds+nbChiffre)=st.st_ino;
		nbChiffre++;
	}
	else return 0;
    
	return size;
}

int
main(int argc, char**argv){
    
	int i=1;
	int taille=0;
	if(argc>1){
		while(i<argc){
			if(strcmp(*(argv+i),"-L")==0){
				opt_follow_links=1;
				printf("Vous avez demandé de suivre des links!\n");
			}
			if(strcmp(*(argv+i),"-b")==0){
				opt_apparent_size=1;
				printf("Vous avez demandé d'afficher les tailles apparentes!\n");
			}
			i++;
		}
	}
	taille=du_file(*(argv+argc-1));
	printf("\nla taille totale est:%d\n\n",taille);
	return 0;
}

