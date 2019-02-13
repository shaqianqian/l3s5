
#include <stdlib.h>
#include <stdio.h>
#include <string.h>

#include "graphe.h"

int main(int argc, char *argv[]) {

  tGraphe graphe;
  //tNumberoSommet sommet;
  int i;
  int count;
  if (argc<2) {
    halt("Usage : %s FichierGraphe\n", argv[0]);
  }


  graphe = grapheAlloue();

  grapheChargeFichier(graphe,  argv[1]);
  count = grapheNbSommets(graphe);
  int max=0;
  int position=0;
  for (i = 0; i < count; i++) {
  //  if(grapheNbVoisinsSommet(graphe,i)==0){
  if(grapheNbVoisinsSommet(graphe,i)>=max){
      max= grapheNbVoisinsSommet(graphe,i);
      position=i;
    //  tNomSommet s;
      //grapheRecupNomSommet(graphe, i, s);
      //printf("%s\n", s);
    }
  }
  //grapheAffiche(graphe);
  tNomSommet s;
  grapheRecupNomSommet(graphe, position, s);
  printf("%s\n", s);
  grapheLibere(graphe);
  exit(EXIT_SUCCESS);
}
