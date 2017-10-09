package fixed;

public enum Operator {
	DIVIDE("\\"), MULTIPLY("*"), SUBTRACT("-"), ADD("+");
	
	private String str;
	
	private Operator(String str) {
		this.str = str;
	}
	
	public String toString() {
		return str;
	}
	
	public double compute(double op1, double op2) {
		switch(this) {
			case DIVIDE:
				return op1 / op2;
			case MULTIPLY:
				return op1 * op2;
			case SUBTRACT:
				return op1 - op2;
			case ADD:
				return op1 + op2;
			default:
				return 0.0;
		}
	}

}
