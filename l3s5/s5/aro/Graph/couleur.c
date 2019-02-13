                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    #include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <assert.h>

#include "graphe.h"
#include "sys/wait.h"

/* Couleurs */
typedef enum {ROUGE=0, BLEU=1, VERT=2} tCouleur;
typedef tCouleur *tTabCouleurs;


void parcoursLargeur(tGraphe graphe, tNumeroSommet depart, tTabCouleurs sommetsParcourus);
void graphe2visuCouleurs(tGraphe graphe, char *outfile, tTabCouleurs tabCouleurs);
void writeGraphe(tGraphe graphe, FILE *fic, tTabCouleurs tabCouleurs);
void writeDiGraphe(tGraphe graphe, FILE *fic, tTabCouleurs tabCouleurs);

int main (int argc, char **argv)
{
  tGraphe graphe;
  tNumeroSommet depart;
  tTabCouleurs sommetsParcourus;
  graphe = grapheAlloue();
  assert(argc == 3);
  grapheChargeFichier(graphe, argv[1]);
  sommetsParcourus = malloc(grapheNbSommets(graphe)*sizeof(tCouleur));
  depart = grapheChercheSommetParNom(graphe, argv[2]);
  assert(depart != -1);
  parcoursLargeur(graphe, depart, sommetsParcourus);
  grapheLibere(graphe);
  free(sommetsParcourus);
  return 0;
}

void parcoursLargeur(tGraphe graphe, tNumeroSommet depart, tTabCouleurs sommetsParcourus)
{
  int i;
  tFileSommets file = fileSommetsAlloue();
  tNumeroSommet x, y;
  for(i = 0; i < grapheNbSommets(graphe); i++)
    sommetsParcourus[i] = BLEU;
  sommetsParcourus[depart] = VERT;
  graphe2visuCouleurs(graphe, "testparcours", sommetsParcourus);
  fileSommetsEnfile(file, depart);
  while(!fileSommetsEstVide(file))
    {
      x = fileSommetsDefile(file);
      for(i = 0; i < grapheNbVoisinsSommet(graphe, x); i++)
	{
	  y = grapheVoisinSommetNumero(graphe, x, i);
	  if(sommetsParcourus[y] == BLEU)
	    {
	      sommetsParcourus[y] = VERT;
	      graphe2visuCouleurs(graphe, "testparcours", sommetsParcourus);
	      fileSommetsEnfile(file, y);
	    }
	}
      sommetsParcourus[x] = ROUGE;
      graphe2visuCouleurs(graphe, "testparcours", sommetsParcourus);
    }
    fileSommetsLibere(file);
  return;
}

void graphe2visuCouleurs(tGraphe graphe, char *outfile, tTabCouleurs tabCouleurs) {
  FILE *fic;
  char commande[80];
  char dotfile[80];  int ret;
  strcpy(dotfile, outfile);
  strcat(dotfile, ".dot");
  fic = fopen(dotfile, "w");
  if (fic==NULL)
    halt ("Ouverture du fichier %s en ecriture impossible\n", dotfile);

  if(grapheEstOriente(graphe))
    writeDiGraphe(graphe, fic, tabCouleurs);
  else
    writeGraphe(graphe, fic, tabCouleurs);


  fclose(fic);
  sprintf(commande, "dot -Tps %s -o %s.ps", dotfile, outfile);
  ret = system(commande);
  if (WEXITSTATUS(ret))
    halt("La commande suivante a echoue\n%s\n", commande);
  printf("Press enter to continue\n");
  getchar();
}

void writeGraphe(tGraphe graphe, FILE *fic, tTabCouleurs tabCouleurs)
{
  tArc arc;
  int i;
  tNomSommet nom;
  tNomSommet or;
  tNomSommet dest;
  char *nomCouleur;
  nomCouleur = "uninitialized";

  fprintf(fic, "graph {\n");

  for(i = 0; i < grapheNbSommets(graphe); i++)
    {
      grapheRecupNomSommet(graphe, i, nom);
      switch(tabCouleurs[i])
	{
	case BLEU:
	  nomCouleur = "blue";
	  break;
	case ROUGE:
	  nomCouleur = "red";
	  break;
	case VERT:
	  nomCouleur = "green";
	  break;
	default:;
	};
      fprintf(fic, "%s [color=%s];\n", nom, nomCouleur);
    }

  for(i = 0; i < grapheNbArcs(graphe); i++)
    {
      arc = grapheRecupArcNumero(graphe, i);
      grapheRecupNomSommet(graphe, arc.orig, or);
      grapheRecupNomSommet(graphe, arc.dest, dest);
      fprintf(fic, "%s -- %s;\n", or, dest);
    }
  fprintf(fic, "}\n");
}

void writeDiGraphe(tGraphe graphe, FILE *fic, tTabCouleurs tabCouleurs)
{
  tArc arc;
  tNomSommet nom;
  tNomSommet or;
  tNomSommet dest;
  int i;
  char *nomCouleur;
  nomCouleur = "uninitialized";

  fprintf(fic, "digraph {\n");

  for(i = 0; i < grapheNbSommets(graphe); i++)
    {
      grapheRecupNomSommet(graphe, i, nom);
      switch(tabCouleurs[i])
	{
	case BLEU:
	  nomCouleur = "blue";
	  break;
	case ROUGE:
	  nomCouleur = "red";
	  break;
	case VERT:
	  nomCouleur = "green";
	  break;
	default:
	  nomCouleur = "undefined";
	};
      fprintf(fic, "%s [color=%s];\n", nom, nomCouleur);
    }

  for(i = 0; i < grapheNbArcs(graphe); i++)
    {
      arc = grapheRecupArcNumero(graphe, i);
      grapheRecupNomSommet(graphe, arc.orig, or);
      grapheRecupNomSommet(graphe, arc.dest, dest);
      fprintf(fic, "%s -> %s;\n", or, dest);
    }
  fprintf(fic, "}\n");
}
