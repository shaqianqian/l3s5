package fil.coo.QUESTIONQIRE_SHA;


public class YesNoAnswer extends Answer {
 String text;
 String type="YesNoAnswer";

public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public YesNoAnswer(String text) {
	super();
	this.text = text;
}
public String toString(){return "(oui/non)";
	}
}
