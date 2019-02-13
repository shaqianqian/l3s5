package fil.coo.QUESTIONQIRE_SHA;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;



public class QuestionaireFactory {
	 String text;
	 String answer;
	 ArrayList<Question>questions;

	String nbPoints;
	
	
public Answer buildAnswer(String ans)
{   try{int v= Integer.parseInt(ans);
		return new Numerique(v);}
	catch(Exception e){
	if("oui".equals(ans.toLowerCase())||"non".equals(ans.toLowerCase()))
	{return new YesNoAnswer(ans); }	
	else return new StringAnswer(ans);


	}	}
	
public Question creatQuestion(String text,String answer,String points) throws IOException
	
{ try{
	int nbpoints=Integer.parseInt(points);
return new Question(text,answer,nbpoints);
	}catch(NumberFormatException e) {throw new IOException("bad format");}
	
	
}
public Questionnaire creatQuestionnaire(String filename) throws IOException
{
   questions=new ArrayList();
   Questionnaire questionnaire=new Questionnaire();
   
   File source =new File(filename);
   BufferedReader bf=null;
   try{bf=new BufferedReader(new FileReader(source));
  
   while((text=bf.readLine())!=null)
   { answer=bf.readLine();
    nbPoints=bf.readLine();
   if(answer==null||nbPoints==null){
	   throw new IOException("bad format");}
      
      questions.add( this.creatQuestion(text, answer, nbPoints)); 
   }}
   catch(FileNotFoundException e){throw new IOException (e);}
   finally{bf.close();} 
	   return questionnaire;
   }


public ArrayList<Question> getQuestions() {
	return questions;
}
public void setQuestions(ArrayList<Question> questions) {
	this.questions = questions;
}


}