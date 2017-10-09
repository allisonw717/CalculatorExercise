package backup;

public enum SingleOperator {
	INVERSE("1 / "), SQRT("SQRT");
	
	private String str;
	
	private SingleOperator(String str) {
		this.str = str;
	}
	
	public String toString() {
		return str;
	}
	
	public double compute(double op1) {
		switch(this) {
			case INVERSE:
				return 1 / op1;
			case SQRT:
				return Math.sqrt(op1);
			default:
				return 0.0;
		}
	}

}

