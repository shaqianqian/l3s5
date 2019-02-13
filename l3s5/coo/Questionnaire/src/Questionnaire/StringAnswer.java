package Questionnaire;

public class StringAnswer extends Answer {
String text;
String type="StringAnswer";

public String getType() {
	return type;
}

public void setType(String type) {
	this.type = type;
}

public StringAnswer(String text) {
	super();
	this.text = text;
}

public String toString(){return "StringAnswer";
}
}
