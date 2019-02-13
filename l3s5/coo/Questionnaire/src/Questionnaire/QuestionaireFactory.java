package Questionnaire;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class QuestionaireFactory {
	
public Question creatQuestion(String text,String answer,String points) throws IOException
	
{ try{
	int nbpoints=Integer.parseInt(points);
return new Question(text,new AnswerFactory().buildAnswer(answer).toString(),nbpoints);
	}catch(NumberFormatException e) {throw new IOException("bad format");}
	
	
}
public Questionnaire creatQuestionnaire(String filename) throws IOException
{
   ArrayList<Question>questions=new ArrayList();
   Questionnaire questionnaire=new Questionnaire();
   
   File source =new File(filename);
   BufferedReader bf=null;
   try{bf=new BufferedReader(new FileReader(source));
   String text;
   while((text=bf.readLine())!=null)
   {String answer=bf.readLine();
   String nbPoints=bf.readLine();
   if(answer==null||nbPoints==null){
	   throw new IOException("bad format");}
       questionnaire.addQuestion(this.creatQuestion(text, answer, nbPoints));  
   }}
   catch(FileNotFoundException e){throw new IOException (e);}
   finally{bf.close();} 
	   return questionnaire;
   }


}