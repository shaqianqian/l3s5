package Questionnaire;

import static org.junit.Assert.*;

import org.junit.Test;

public class AnswerFactoryTest {

	@Test
	public void test() {
		AnswerFactory af=new AnswerFactory();
		
		Answer s=af.buildAnswer("aabb");
		System.out.println(s);
		s=af.buildAnswer("123");
		System.out.println(s);
		s=af.buildAnswer("oui");
		System.out.println(s);
	}

}
