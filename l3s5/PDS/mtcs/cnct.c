/* ------------------------------
   $Id: cnct.c,v 1.3 2007/04/04 15:18:50 marquet Exp $
   ------------------------------------------------------------

   mtcs - a multithreaded chat serveur
   Philippe Marquet, Apr 2005

   Gestion de la connexion d'un client par un thread
*/

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <assert.h>
#include <pthread.h>


#include "config.h"
#include "tools.h"
#include "cnct.h"
#include "stat.h"
/* Gestion des sockets */
static int sockets[MAX_CONNECTION]; /* tableau initialisé a zero */
static statistiques_t statistiques;

static void
add_socket(int fd)
{
    int i;
   
    pgrs_in();

   
    for (i=0; i<MAX_CONNECTION; i++) {
	if (sockets[i] == 0) {
	    sockets[i] = fd;
	    break;
	}
    }
    
    assert(i!=MAX_CONNECTION);
    pgrs_out();
}

static void
del_socket(int fd)
{
    int i;
    pgrs_in();
    
    statistiques.nb_clients --;

    for (i=0; i<MAX_CONNECTION; i++) {
	if (sockets[i] == fd) {
	    sockets[i] = 0;
	    break;
	}
    }
    assert(i!=MAX_CONNECTION);
    pgrs_out();
}

 void print(){
	print_info(&statistiques);
}


/* Un client  */
static void
repeater(int sckt)
{
    char buf[MAX_BUFFER];
    int nbc, i,result;
    const char WELCOME[] = "mtcs : bienvenu\n";
      

    pthread_mutex_t m;
    statistiques.max_lignes_recues=1000;
    statistiques.max_lignes_envoyees=1000;
    statistiques.max_client=20;
    
    result = pthread_mutex_init(&m,NULL);/*init mutex*/
    if(result !=0){
        perror("pthread_mutex_inti");
        exit(EXIT_FAILURE);
    }

    pgrs_in();
    write(sckt, WELCOME, strlen(WELCOME));

    pgrs("enregistrement d'une socket");
    add_socket(sckt);
    statistiques.nb_clients++;
    if (statistiques.nb_clients>statistiques.max_client)
	perror("too much client");
    pthread_mutex_lock(&m);/*lock*/    


    while (1) {
	pgrs("attente read");
	nbc = read(sckt, buf, MAX_BUFFER);

	
	
	if (nbc <= 0) {
	    pgrs("fin lecture client");
	    pgrs("desenregistrement d'une socket");
	    del_socket(sckt);
	    close(sckt);

 	    pthread_mutex_unlock(&m);/*unlock*/

            pgrs_out();
	    return;
	}
	pgrs("boucle ecriture");
	for(i=0; i<MAX_CONNECTION; i++){
	    if (sockets[i]){
		write(sockets[i], buf, nbc);	        
		statistiques.nb_lignes_recues[i]++;
            if(statistiques.nb_lignes_recues[i] > 
                statistiques.max_lignes_recues){
                  perror("max recue");
                  exit(EXIT_FAILURE);
		}
	}
       if(sockets[i] == sckt){
                statistiques.nb_lignes_envoyees[i]++;
                if(statistiques.nb_lignes_envoyees[i] > 
                    statistiques.max_lignes_envoyees){
                    perror("max envoye");
		    exit(EXIT_FAILURE);
               }
            }
       }
	pgrs("fin boucle ecriture");
    }
}


void *pthread_function(void *arg){
    
    int fd_no = (unsigned long int)arg;
    repeater(fd_no);
    sleep(1);
    pthread_exit(NULL);
}



/* Création d'un client */
/* Version stupide. Pas de creation de thread, 
   Le serveur ne peut plus accepter de connexion car il gère
   l'interaction avec le premier client. 
*/
int
manage_cnct(int fd)
{   
    pthread_t thread; 
    int status;
    
    pgrs_in();
    int fd_no = fd;

   

    status=pthread_create(&thread,NULL,pthread_function,(void *)fd_no);
    assert(status==0);
    pgrs_out();
    return 0;
}

