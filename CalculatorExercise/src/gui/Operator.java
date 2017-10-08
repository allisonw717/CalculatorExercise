package gui;

public enum Operator {
	DIVIDE("\\"), MULTIPLY("*"), SUBTRACT("-"), ADD("+"), INVERSE("1 / "), SQRT("SQRT");
	
	private String str;
	
	private Operator(String str) {
		this.str = str;
	}
	
	public String toString() {
		return str;
	}

}
