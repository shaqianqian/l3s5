package fil.coo.QUESTIONQIRE_SHA;


import java.util.ArrayList;

public  class Questionnaire {
ArrayList<Question>questions=new ArrayList();

public void addQuestion(Question q) {
   
	questions.add(q);

}
public String toString(){
	return questions+"";
	
	
}
 }