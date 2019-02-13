/* Exemple 1 */
package exemple1;

%%

%unicode

/*MOT_USUEL=[:letter:]+*/

ENTIER_SIMPLE=[0-9]+
IDENTIFICATEURS=([A-Za-z][A-Za-z0-9]*)
OPERATEURS=[-+*/]

%% 

{ENTIER_SIMPLE}|{IDENTIFICATEURS}|{OPERATEURS}
      {return new Yytoken(yytext());}


[^[:letter:]0-9]+
      {}  
