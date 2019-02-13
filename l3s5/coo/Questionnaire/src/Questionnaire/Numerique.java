package Questionnaire;

public class Numerique extends Answer {
int n;
String type="numerique";
public String getType() {
	return type;
}

public void setType(String type) {
	this.type = type;
}

public Numerique(int n) {
	super();
	this.n = n;
}

public int getN() {
	return n;
}

public void setN(int n) {
	this.n = n;
}
public String toString(){return "numerique";
}
}
