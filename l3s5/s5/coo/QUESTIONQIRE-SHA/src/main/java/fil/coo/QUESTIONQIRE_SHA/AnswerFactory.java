package fil.coo.QUESTIONQIRE_SHA;


public class AnswerFactory  {
public Answer buildAnswer(String ans)
{   try{int v= Integer.parseInt(ans);
	return new Numerique(v);}
catch(Exception e){
if("oui".equals(ans.toLowerCase())||"non".equals(ans.toLowerCase())){return new YesNoAnswer(ans); }	
else return new StringAnswer(ans);


}
}



}


