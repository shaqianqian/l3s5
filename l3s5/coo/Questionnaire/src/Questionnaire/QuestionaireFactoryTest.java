package Questionnaire;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class QuestionaireFactoryTest {

	@Test
	public void test() throws IOException {
		 QuestionaireFactory qf=new  QuestionaireFactory();
      System.out.println(qf.creatQuestion("zegq", "fgq", "3"));
    Questionnaire aa=qf.creatQuestionnaire("/Users/shaqianqian/Documents/workspace/Questionnaire/src/Questionnaire/question_tolkien_2.txt");
      System.out.println(aa);
      
	} 
	
	
	}