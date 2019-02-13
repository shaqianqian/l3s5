package jadelex;

public class StepLength extends BaseToken {
	private int length;

	public int getLength() {
		return length;
	}

	public StepLength(int length) {
		super(TokenType.STEP_LENGTH);
		this.length = length;
	}

	public String toString() {
		return super.toString() + "[" + length + "]";
	}
}