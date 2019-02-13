package jadelex;

public class PenMode extends BaseToken {
	private boolean on;

	public boolean getMode() {
		return on;
	}

	public PenMode(boolean on) {
		super(TokenType.PEN_MODE);
		this.on = on;
	}

	public String toString() {
		return super.toString() + "[" + on + "]";
	}
}