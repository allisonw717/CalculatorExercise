package guifinal;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public enum DecimalOperator {
	 DECIMAL(".");
	 
	 private String str;
	 private NumberFormat nf = new DecimalFormat("###################.####################");
	 
	 private DecimalOperator(String str) {
		this.str = str;
	}
		
	public String toString() {
		return str;
	}
	
	//returns decimal, where op2 appends onto op1
	public double compute(double op1, double op2) {
		if(nf.format(op1).contains(".")) {
			return Double.parseDouble(nf.format(op1) + ((int) op2));
		}
		return Double.parseDouble(nf.format(op1) + "." + ((int) op2));
	}
}
