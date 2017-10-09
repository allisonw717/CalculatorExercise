package guifinal;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import javafx.application.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * 
 * @authors Annie Fang, Allison Wei, Rachelle Sims
 *
 */

/*
 * This class runs a calculator application using state design pattern. 
 * The calculator does not account for the negation operation.
 */
@SuppressWarnings("restriction")
public class CalculatorGUI extends Application{
	//declare display, used in both start() and various inner classes
	
	TextField display;
	
	//instantiate Calculator State objects
	CalculatorState operatorState = new OperatorState();
	CalculatorState twoOperationState = new TwoOperationState();
	CalculatorState secondOperandState = new SecondOperandState();
	CalculatorState excessOperatorState = new ExcessOperatorState();
	CalculatorState decimalState = new DecimalState();
	CalculatorState secondDecimalState = new SecondDecimalState();
	
	//instantiate current state
	CalculatorState currentState = operatorState;
	
	//stores information from user interaction with calculator
	double operand1, operand2;
	Operator twoOperation;
	SingleOperator oneOperation;
	DecimalOperator decimalOperation;
	
	//number formatting object used for double primitives to display to calculator
	NumberFormat nf = new DecimalFormat("###################.####################");
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		GridPane pane = new GridPane();
		pane.setVgap(5);
		pane.setHgap(5);
		for(int col = 0; col < 4; col++) {
			pane.getColumnConstraints().add(new ColumnConstraints(50));
		}
		
		/*declare and instantiate all the buttons*/
		Button clear = new Button("C");
		Button clearE = new Button("CE");
		Button inverse = new Button("1/x");
		Button sqrt = new Button("\u221Ax");
		Button divide = new Button("/");
		Button multiply = new Button("*");
		Button add = new Button("+");
		Button subtract = new Button("-");
		Button equals = new Button("=");
		Button one = new Button("1");
		Button two = new Button("2");
		Button three = new Button("3");
		Button four = new Button("4");
		Button five = new Button("5");
		Button six = new Button("6");
		Button seven = new Button("7");
		Button eight = new Button("8");
		Button nine = new Button("9");
		Button zero= new Button("0");
		Button decimal = new Button(".");
		
		//setMaxWidth for each button
		clear.setMaxWidth(Double.MAX_VALUE);
		clearE.setMaxWidth(Double.MAX_VALUE);
		inverse.setMaxWidth(Double.MAX_VALUE);
		sqrt.setMaxWidth(Double.MAX_VALUE);
		divide.setMaxWidth(Double.MAX_VALUE);
		multiply.setMaxWidth(Double.MAX_VALUE);
		add.setMaxWidth(Double.MAX_VALUE);
		subtract.setMaxWidth(Double.MAX_VALUE);
		equals.setMaxWidth(Double.MAX_VALUE);
		one.setMaxWidth(Double.MAX_VALUE);
		two.setMaxWidth(Double.MAX_VALUE);
		three.setMaxWidth(Double.MAX_VALUE);
		four.setMaxWidth(Double.MAX_VALUE);
		five.setMaxWidth(Double.MAX_VALUE);
		six.setMaxWidth(Double.MAX_VALUE);
		seven.setMaxWidth(Double.MAX_VALUE);
		eight.setMaxWidth(Double.MAX_VALUE);
		nine.setMaxWidth(Double.MAX_VALUE);
		zero.setMaxWidth(Double.MAX_VALUE);
		decimal.setMaxWidth(Double.MAX_VALUE);
		
		/*add everything to GridPane*/
		pane.add(clear,	0, 0);
		pane.add(clearE, 1, 0);
		pane.add(inverse , 2,0);
		pane.add(sqrt, 3, 0);
		pane.add(seven, 0, 1);
		pane.add(eight, 1, 1);
		pane.add(nine, 2, 1);
		pane.add(divide, 3, 1);
		pane.add(four, 0, 2);
		pane.add(five, 1, 2);
		pane.add(six, 2, 2);
		pane.add(multiply, 3, 2);
		pane.add(one, 0, 3);
		pane.add(two, 1, 3);
		pane.add(three, 2, 3);
		pane.add(subtract, 3, 3);
		pane.add(zero, 1, 4);
		pane.add(equals, 2, 4);
		pane.add(add, 3, 4);
		pane.add(decimal, 0, 4);
		
		//instantiate display and set properties
		display = new TextField();
		display.setAlignment(Pos.BASELINE_RIGHT);
		
		VBox calc = new VBox(10);
		calc.setPadding(new Insets(10,10,10,10));
		calc.getChildren().addAll(display,pane);
		
		
		//lambda expressions for all Button events
		one.setOnAction(e -> {
			currentState.enterOperand(1.0);
		});
		
		two.setOnAction(e -> {
			currentState.enterOperand(2.0);
		});
		
		three.setOnAction(e -> {
			currentState.enterOperand(3.0);
		});
		
		four.setOnAction(e -> {
			currentState.enterOperand(4.0);

		});
		
		five.setOnAction(e -> {
			currentState.enterOperand(5.0);
		});
		
		six.setOnAction(e -> {
			currentState.enterOperand(6.0);
		});
		
		seven.setOnAction(e -> {
			currentState.enterOperand(7.0);
		});
		
		eight.setOnAction(e -> {
			currentState.enterOperand(8.0);
		});
		
		nine.setOnAction(e -> {
			currentState.enterOperand(9.0);

		});
		
		zero.setOnAction(e -> {
			currentState.enterOperand(0.0);
		});
		
		divide.setOnAction(e -> {
			currentState.enterOperator(Operator.DIVIDE);
		});
		
		multiply.setOnAction(e -> {
			currentState.enterOperator(Operator.MULTIPLY);
		});
		
		subtract.setOnAction(e -> {
			currentState.enterOperator(Operator.SUBTRACT);
		});
		
		add.setOnAction(e -> {
			currentState.enterOperator(Operator.ADD);
		});
		
		decimal.setOnAction(e -> {
			currentState.enterOperator(DecimalOperator.DECIMAL);
		});
		
		inverse.setOnAction(e -> {
			currentState.enterOperator(SingleOperator.INVERSE);
		});
		
		sqrt.setOnAction(e -> {
			currentState.enterOperator(SingleOperator.SQRT);
		});
		
		clear.setOnAction(e -> {
			currentState.clear();
		});
		
		clearE.setOnAction(e -> {
			currentState.clearEntry();
		});
		
		equals.setOnAction(e -> {
			currentState.compute();
		});
		
		//creates scene and sets scene properties
		Scene scene = new Scene(calc, 235, 213);
		primaryStage.setTitle("Calculator");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	//launches application
	public static void main (String args[]) {
		Application.launch(args);
	}
	
	/*
	 * initial CalculatorState, can either hit operand buttons or operator button
	 * takes 0 as first operand if operator button is pushed
	 */
	public class OperatorState implements CalculatorState {
		//adds on digits to input
		public void enterOperand(double operand) {
			operand1 = operand1*10 + operand;
			display.setText(nf.format(operand1));
		}
		
		//changes state to wait for second operand
		public void enterOperator(Operator operator) {
			twoOperation = operator;
			display.setText(nf.format(operand1));
			currentState = secondOperandState;
		}
		
		//immediately evaluates and displays a single input operation
		public void enterOperator(SingleOperator operator) {
			oneOperation = operator;
			operand1 = oneOperation.compute(operand1);
			display.setText(nf.format(operand1));
			currentState = excessOperatorState;
		}
		
		//changes state to wait for decimal digits
		public void enterOperator(DecimalOperator operator) {
			decimalOperation = operator;
			currentState = decimalState;
		}
		
		//expression not complete if in this state, so compute() does nothing
		public void compute() {}
		
		public void clear() {
			operand1 = 0.0;
			operand2 = 0.0;
			oneOperation = null;
			twoOperation = null;
			decimalOperation = null;
			display.setText("0");
		}
		
		public void clearEntry() {
			operand1 = 0.0;
			display.setText(nf.format(operand1));
		}
	}
	
	public class DecimalState implements CalculatorState {
		//adds on incoming digits to make decimal input
		public void enterOperand(double operand) {
			operand1 = decimalOperation.compute(operand1, operand);
			display.setText(nf.format(operand1));
		}
		
		//changes state to wait for second operand
		public void enterOperator(Operator operator) {
			twoOperation = operator;
			currentState = secondOperandState;
		}
		
		//immediately evaluates and displays any operations that take a single input
		public void enterOperator(SingleOperator operator) {
			oneOperation = operator;
			operand1 = oneOperation.compute(operand1);
			display.setText(nf.format(operand1));
			currentState = excessOperatorState;
		}
		
		//cannot add another decimal to an operand that is already a decimal, so does nothing
		public void enterOperator(DecimalOperator operator) {}
		
		//expression not complete, so compute() does nothing
		public void compute() {}
		
		public void clear() {
			operand1 = 0.0;
			operand2 = 0.0;
			oneOperation = null;
			twoOperation = null;
			decimalOperation = null;
			display.setText("0");
			currentState = operatorState;
		}
		
		public void clearEntry() {
			operand1 = 0.0;
			operand2 = 0.0;
			display.setText(nf.format(operand1));
			currentState = operatorState;
		}
	}
	
	public class SecondDecimalState implements CalculatorState {
		//adds on incoming digits to make decimal input
		public void enterOperand(double operand) {
			operand2 = decimalOperation.compute(operand2, operand);
			display.setText(nf.format(operand2));
		}
		
		//changes state to wait for second operand
		public void enterOperator(Operator operator) {
			compute();
			twoOperation = operator;
			currentState = twoOperationState;
		}
		
		//immediately evaluates and displays any operations that take a single input
		public void enterOperator(SingleOperator operator) {
			oneOperation = operator;
			operand1 = oneOperation.compute(operand1);
			display.setText(nf.format(operand1));
			currentState = excessOperatorState;
		}
		
		//cannot add another decimal to an operand that is already a decimal, so does nothing
		public void enterOperator(DecimalOperator operator) {}
		
		//if SecondDecimalState reached, second operand has been input, or else default 0 assumed as second operand
		public void compute() {
			operand1 = twoOperation.compute(operand1, operand2);
			display.setText(nf.format(operand1));
			operand2 = 0.0;
			twoOperation = null;
			currentState = excessOperatorState;
		}
		
		public void clear() {
			operand1 = 0.0;
			operand2 = 0.0;
			oneOperation = null;
			twoOperation = null;
			decimalOperation = null;
			display.setText("0");
			currentState = operatorState;
		}
		
		public void clearEntry() {
			operand1 = 0.0;
			operand2 = 0.0;
			display.setText(nf.format(operand1));
			currentState = operatorState;
		}
	}
	
	public class SecondOperandState implements CalculatorState {
		//inputs second operand, changes state ready for completion or continuation of expression
		public void enterOperand(double operand) {
			operand2 = operand2*10 + operand;
			display.setText(nf.format(operand2));
			currentState = twoOperationState;
		}
		
		//waiting for second operand, cannot take another operator, so method does nothing
		public void enterOperator(Operator operator) {}
		
		//waiting for second operand, cannot take another operator, so method does nothing
		public void enterOperator(SingleOperator operator) {}
		
		//changes state to SecondDecimalState to handle decimal operand
		public void enterOperator(DecimalOperator operator) {
			decimalOperation = operator;
			currentState = secondDecimalState;
		}
		
		//waiting for second operand, cannot take another operator, so method does nothing
		public void compute() {}
		
		public void clear() {
			operand1 = 0.0;
			operand2 = 0.0;
			oneOperation = null;
			twoOperation = null;
			decimalOperation = null;
			display.setText("0");
			currentState = operatorState;
		}
		
		public void clearEntry() {
			operand2 = 0.0;
			display.setText(nf.format(operand2));
		}
		
	}
	
	public class TwoOperationState implements CalculatorState {
		//continues to add digits to operand
		public void enterOperand(double operand) {
			operand2 = operand2*10 + operand;
			display.setText(nf.format(operand2));
		}
		
		//computes expression, changes state to wait for next operand
		public void enterOperator(Operator operator) {
			compute();
			twoOperation = operator;
			currentState = secondOperandState;
		}
		
		//immediately evaluates and displays any operations that take a single input
		public void enterOperator(SingleOperator operator) {
			compute();
			oneOperation = operator;
			operand1 = oneOperation.compute(operand1);
			display.setText(nf.format(operand1));
			currentState = excessOperatorState;
		}
		
		//changes state to SecondDecimalState to handle decimal operand
		public void enterOperator(DecimalOperator operator) {
			decimalOperation = operator;
			currentState = secondDecimalState;
		}
		
		//computes expression and displays result, changes state to wait for next operator
		public void compute() {
			operand1 = twoOperation.compute(operand1, operand2);
			display.setText(nf.format(operand1));
			operand2 = 0.0;
			twoOperation = null;
			currentState = excessOperatorState;
		}
		
		public void clear() {
			operand1 = 0.0;
			operand2 = 0.0;
			oneOperation = null;
			twoOperation = null;
			decimalOperation = null;
			display.setText("0");
			currentState = operatorState;
		}
		
		public void clearEntry() {
			operand2 = 0.0;
			display.setText(nf.format(operand2));
			currentState = secondOperandState;
		}
	
	}
	
	/*
	 * state when full expression with single operation exists, but more operators are hit
	 */
	public class ExcessOperatorState implements CalculatorState {
		//full expression already exists, more operands cannot be added
		public void enterOperand(double operand) {}
		
		//changes state to wait for next operand
		public void enterOperator(Operator operator) {
			twoOperation = operator;
			currentState = secondOperandState;
		}
		
		//immediately computes single expression and waits for next operator
		public void enterOperator(SingleOperator operator) {
			oneOperation = operator;
			operand1 = oneOperation.compute(operand1);
			display.setText(nf.format(operand1));
			currentState = excessOperatorState;
		}
		
		//cannot create decimals at the state
		public void enterOperator(DecimalOperator operator) {}
		
		//cannot compute (other states already computed/waiting specifically for an operator)
		public void compute() {}
		
		public void clear() {
			operand1 = 0.0;
			operand2 = 0.0;
			oneOperation = null;
			twoOperation = null;
			decimalOperation = null;
			display.setText("0");
			currentState = operatorState;
		}
		
		public void clearEntry() {
			operand1 = 0.0;
			operand2 = 0.0;
			display.setText(nf.format(operand2));
			currentState = operatorState;
		}
	}
}

