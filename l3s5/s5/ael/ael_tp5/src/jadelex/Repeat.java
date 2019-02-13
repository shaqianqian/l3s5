package jadelex;

public class Repeat extends BaseToken {
	private int occurences;

	public int getOccurences() {
		return this.occurences;
	}

	public Repeat(int occurences) {
		super(TokenType.REPEAT);
		this.occurences = occurences;
	}

	public String toString() {
		return super.toString() + "[" + this.occurences + "]";
	}
}