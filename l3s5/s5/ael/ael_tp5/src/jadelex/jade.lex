package jadelex ;

%%

%class TokenizerV1
%implements Tokenizer
%public
%unicode
%line
%column

MOVE=baisser|lever
NORD="nord"
OUEST="ouest"
SUD="sud"
EST="est"
STEP_LENGTH="pas"\s[0-9]+
JUMP="up"\s[0-9]+\s[0-9]+
%%
{MOVE}
      {return new PenMode(true);  }


{NORD}
	{return new Move(Direction.NORTH);}

{SUD}
	{return new Move(Direction.SOUTH);}

{EST}
	{return new Move(Direction.EAST);}

{OUEST}
	{return new Move(Direction.WEST);}

{STEP_LENGTH}
	  {return new StepLength(Integer.parseInt(yytext().split(" ")[1]));}
{JUMP}
	{String[] parts = yytext().split(" ");
	return new Jump(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));}
{REPEAT}
	{return new Repeat(Integer.parseInt(yytext().split(" ")[0]));}
