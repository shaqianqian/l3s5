package fil.coo.QUESTIONQIRE_SHA;



import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JTextField;
public class App {
	   
	public static void main(String[] args) throws IOException {
  	final QuestionaireFactory bf=new QuestionaireFactory();

  	
  	bf.creatQuestionnaire("src/main/java/question_tolkien_2.txt");
		final JFrame test=new JFrame("test");
		test.setVisible(true);
		test.setLocationRelativeTo(null);
		test.setBounds(200, 0, 1000, 400);//x,y,weight,height
		test.setDefaultCloseOperation(test.EXIT_ON_CLOSE);
		test.setLayout(null);
			//la premiere question
		 JButton submit=new JButton("fini");
		 JLabel label8 = new JLabel("<html>votre pointe est?</html>");
		 label8.setBounds(300, 250, 250, 70);
		 test.add(submit);
		 submit.setBounds(330, 300, 50, 40);
		 test.add(label8);
		 label8.repaint();
		
	   JLabel []labels =new JLabel[bf.getQuestions().size()] ;
	   final JTextField texts[]=new JTextField[bf.getQuestions().size()];
		
	   int k=0;	
	
	   while(k<bf.getQuestions().size()){
		   AnswerFactory af=new AnswerFactory();
		   int qte=k+1;
	      labels[k] = new JLabel("Q"+qte+": "+bf.getQuestions().get(k).getText()+"  (point:"+bf.getQuestions().get(k).getPoint()+")  (type "+af.buildAnswer(bf.getQuestions().get(k).getAnswer())+")");
		  labels[k].setBounds(0, k*30+10, 800, 30);
		  labels[k].repaint();
		   test.add(labels[k]); 
		 texts[k]=new JTextField();
		   texts[k].setBounds(800, k*30+10, 100, 30);
		 
		   test.add(texts[k]);
		   labels[k].repaint();
		    texts[k].repaint();
		  
		    
		  k++;

	   	   }
	 
   	  
	   submit.addMouseListener(new MouseAdapter() {
				@Override
			public void mouseClicked(MouseEvent e) {
					int g=0;int point=0;
					for(g=0;g<bf.getQuestions().size();g++){
					if( texts[g].getText().equals(bf.getQuestions().get(g).getAnswer()))
				    {
						
						point+=bf.getQuestions().get(g).getPoint(); }
						 
							  }
					
				   final JLabel label9 = new JLabel();
				  label9.setBounds(450, 250, 250, 70);
		           
					label9.setText(String.valueOf(point));
					test.add(label9);
					label9.repaint();
				}
			
			});
	  	  
			
			}
	   

	}

