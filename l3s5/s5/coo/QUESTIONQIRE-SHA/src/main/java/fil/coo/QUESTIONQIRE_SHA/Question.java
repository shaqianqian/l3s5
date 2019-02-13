package fil.coo.QUESTIONQIRE_SHA;


public class Question {
	String text;
    String answer;
   
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public Question(String text, String answer, int point) {
		super();
		this.text = text;
		this.answer = answer;
		this.point = point;
	}
	int point;
public String toString(){return text+""+answer+""+point+"\n"; }

}
