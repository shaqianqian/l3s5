package jadelex;

class BaseToken implements Yytoken {
	private final TokenType type;

	public TokenType getType() {
		return type;
	}

	protected BaseToken(TokenType type) {
		this.type = type;
	}

	public String toString() {
		return "<" + type + ">";
	}
}