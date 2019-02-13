#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "graphe.h"
#include "sys/wait.h"
#include <assert.h>
#include <sys/time.h>  `

void graphe2visu(tGraphe graphe, char *outfile);
void writeGraphe(tGraphe graphe, FILE *fic);
void writeDiGraphe(tGraphe graphe, FILE *fic);

int main (int argc, char **argv)
{
  tGraphe graphe;
  graphe = grapheAlloue();
  if (argc<2) {
    halt("Usage : %s FichierGraphe\n", argv[0]);
  }
  grapheChargeFichier(graphe, argv[1]);
  graphe2visu(graphe, argv[2]);
  grapheLibere(graphe);

  return 0;
}

/* Necessaire pour la macro WEXITSTATUS */
void graphe2visu(tGraphe graphe, char *outfile) {
  FILE *fic;
  char commande[80];
  char dotfile[80]; /* le fichier dot pour creer le ps */
  int ret;
  /* on va creer un fichier pour graphviz, dans le fichier "outfile".dot */
  strcpy(dotfile, outfile);
  strcat(dotfile, ".dot");
  fic = fopen(dotfile, "w");
  if (fic==NULL)
    halt ("Ouverture du fichier %s en ecriture impossible\n", dotfile);

  if(grapheEstOriente(graphe))
    writeDiGraphe(graphe, fic);
  else
    writeGraphe(graphe, fic);

  fclose(fic);
  sprintf(commande, "dot -Tps %s -o %s.ps", dotfile, outfile);
  ret = system(commande);
  if (WEXITSTATUS(ret))
    halt("La commande suivante a echoue\n%s\n", commande);
}

void writeDiGraphe(tGraphe graphe, FILE *fic)
{
  tArc arc;
  tNomSommet or;
  tNomSommet dest;
  int i;
    
    fprintf(fic, "digraph {\n");
  for(i = 0; i < grapheNbArcs(graphe); i++)
    {
      arc = grapheRecupArcNumero(graphe, i);
      grapheRecupNomSommet(graphe, arc.orig, or);
      grapheRecupNomSommet(graphe, arc.dest, dest);
      fprintf(fic, "%s -> %s;\n", or, dest);
    }
  fprintf(fic, "}\n");
}

void writeGraphe(tGraphe graphe, FILE *fic)
{
  tArc arc;
  int i;
  tNomSommet or;
  tNomSommet dest;
  fprintf(fic, "graph {\n");
  for(i = 0; i < grapheNbArcs(graphe); i++)
    {
      arc = grapheRecupArcNumero(graphe, i);
      grapheRecupNomSommet(graphe, arc.orig, or);
      grapheRecupNomSommet(graphe, arc.dest, dest);
      fprintf(fic, "%s -- %s;\n", or, dest);


    }
  fprintf(fic, "}\n");
}
