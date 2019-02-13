

#ifndef _STAT_H_
#define _STAT_H_


typedef struct {
    int nb_clients;
     int nb_lignes_recues[30];
     int nb_lignes_envoyees[30];
     int max_client;
     int max_lignes_recues;
     int max_lignes_envoyees;
}statistiques_t;


extern void print_info(statistiques_t *s);

#endif
