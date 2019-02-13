#include <sys/types.h>
#include <sys/stat.h>
#include <dirent.h>
#include <unistd.h>
#include <stdio.h>	
#include <string.h>
#include <stdlib.h>
#include <limits.h>

	static int opt_apparent_size=0;
	static int opt_follow_links=0;

	static int LesNoeuds[200]; 
	static int nbChiffre=0;
	/*commentaire*/
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
	while(((*(des+(i++)))==s)&&(i<n+1)) found=1;
	return found;
}
/*pour éviter de calculer un même fichier( au niveau du noeud ) plusieur fois*/
/*s'il y a un lien symbolique qui indiquer fichier X et X est calculé avant*/
/*on va passer ce fichier et ne calcule rien (0) */

int du_file(const char*pathname){
	
	struct stat sb;
	struct dirent *dp;
	DIR *dirp;
	int size=0;
	int status;
	status = lstat(pathname,&sb);

	char* copie;
	copie=(char*)malloc(sizeof(char)*200);

	if(status){
		perror("apple de stat");
		return 0;
	}	


	if (S_ISREG(sb.st_mode)){
		if(!opt_apparent_size)		
			size=sb.st_blocks;
		else size=sb.st_size;
	}

	else if(S_ISLNK(sb.st_mode)){
		if(opt_follow_links){
			stat(pathname,&sb);
			/*on suit le lien*/
			if(status){
				perror("apple de stat");
				return 0;
			}
			if(!opt_apparent_size)		
				size=sb.st_blocks;
			else size=sb.st_size;
		}
		else{
			/*si on n'est pas demandé de suivre ce lien on retourne la taille de ce lien lui-même*/
			if(!opt_apparent_size)		
				size=sb.st_blocks;
			else size=sb.st_size;
		}
	}
	
	else if(S_ISDIR(sb.st_mode)){
		size=sb.st_size;

		if((dirp=opendir(pathname))==NULL){
			perror("couldn't open it");
			return 0;
		}
	
		while(dp=readdir(dirp)){
			
			strcpy(copie,pathname);

			if(valid_name(dp->d_name)){
				strcat(copie,"/");
				strcat(copie,dp->d_name);
				size+=du_file(copie);
				
			}
		}
		closedir(dirp);
				
	}
	
	if(!findChiffre(LesNoeuds,sb.st_ino,nbChiffre)){
		*(LesNoeuds+nbChiffre)=sb.st_ino;
		nbChiffre++;
	}
	else return 0;
	
	printf("path:%s ",pathname);
	printf("taille:%d\n",size);
	/*afficher les détailles des fichiers qu'on calcul pour faire le processus de calcul plus claire et précis*/

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
	

























