package postfixees;

%%

%unicode
%line
%column

ENTIER_SIMPLE=[0-9]+
PLUS=[+]|plus
MINUS=[-]|minus
MULT=[\*]|mult
DIV=[/]|div

%% 

{ENTIER_SIMPLE}
      { return new Valeur(yytext()); }

{PLUS}
      { return new Plus(yytext()); }

{MINUS}
      { return new Minus(yytext()); }
      
{MULT}
      { return new Mult(yytext()); }

{DIV}
      { return new Div(yytext()); }

/* ajouter le cas des espaces et fins de ligne */
	  

/* ajouter les autres tokens */
