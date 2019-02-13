package fil.coo.QUESTIONQIRE_SHA;

import java.io.IOException;

import org.junit.Test;

public class Snippet {
	@Test
		public void test() throws IOException {
	 QuestionaireFactory qf=new  QuestionaireFactory();
	  System.out.println(qf.creatQuestion("zegq", "fgq", "3"));
	      
	 Questionnaire aa= qf.creatQuestionnaire("src/question_tolkien_2.txt");
		System.out.println(aa);
		
		
		} 
}

