package guifinal;

public interface CalculatorState {
	public void enterOperand(double operand);
	public void enterOperator(Operator operator);
	public void enterOperator(SingleOperator operator);
	public void enterOperator(DecimalOperator operator);
	public void compute();
	public void clear();
	public void clearEntry();
	
}