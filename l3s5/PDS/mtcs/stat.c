#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>

#include "stat.h"


void print_info(statistiques_t *s){
    int i;
    printf("nombre clients: %d\n",s->nb_clients);
    for( i = 0 ; i < s->nb_clients ; i ++){
        printf("client %d nombre lignes recues: %d\n",i+1,s->nb_lignes_recues[i]);
        printf("client %d nombre lignes envoyees: %d\n",i+1,s->nb_lignes_envoyees[i]);
    }
 
    printf("max clients: %d\n",s->max_client);
    printf("max lignes recues: %d\n",s->max_lignes_recues);
    printf("max lignes envoyees: %d\n",s->max_lignes_envoyees);
    printf("\n");
    return;
}
