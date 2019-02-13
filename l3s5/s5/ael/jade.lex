package jadelex ;

%%

%class TokenizerV1
%implements Tokenizer
%public
%unicode
MOT=baisser
MOT2=lever

%line
%column


%%

{MOT}
{
Yytoken t=new PenMode(true);
return t;
}
{MOT2}
{
Yytoken t=new PenMode(false);
return t;
}

\s
{}

. 
{return new Unknown(yytext());}
