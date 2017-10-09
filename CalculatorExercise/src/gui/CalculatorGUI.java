package gui;

import java.util.ArrayList;

import javafx.application.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * 
 * @authors Annie Fang, Allison Wei, Rachelle Sims
 *
 */
public class CalculatorGUI extends Application{
	TextField display;
	
	CalculatorState operatorState = new OperatorState();
	CalculatorState oneOperationState = new OneOperationState();
	CalculatorState twoOperationState = new TwoOperationState();
//	CalculatorState secondOperandState = new SecondOperandState();
//	CalculatorState excessOperatorState = new ExcessOperatorState();
	
	CalculatorState currentState = operatorState;
	
	double operand1, operand2;
	Operator twoOperation;
	SingleOperator oneOperation;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		/*create and format GridPane*/
		GridPane pane = new GridPane();
		pane.setVgap(5);
		pane.setHgap(5);
		for(int col = 0; col < 4; col++) {
			pane.getColumnConstraints().add(new ColumnConstraints(50));
		}
		
		/*create all the buttons this is so gross*/
		Button clear = new Button("C");
		Button clearE = new Button("CE");
		Button inverse = new Button("1/x");
		Button sqrt = new Button("√x");
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
		
		/*I don't know how to do this better I'm sorry*/
		ArrayList<Button> buttons = new ArrayList<Button>();
		buttons.add(clear);
		buttons.add(clearE);
		buttons.add(inverse);
		buttons.add(sqrt);
		buttons.add(divide);
		buttons.add(multiply);
		buttons.add(add);
		buttons.add(subtract);
		buttons.add(equals);
		buttons.add(one);
		buttons.add(two);
		buttons.add(three);
		buttons.add(four);
		buttons.add(five);
		buttons.add(six);
		buttons.add(seven);
		buttons.add(eight);
		buttons.add(nine);
		buttons.add(zero);
		buttons.add(decimal);
		
		/* for the a e s t h e t i c */
		for(Button b: buttons) {
			b.setMaxWidth(Double.MAX_VALUE);
		}
		
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
		pane.add(decimal,0,4);
		pane.add(zero, 1, 4);
		pane.add(equals, 2, 4);
		pane.add(add, 3, 4);
		
		
		display = new TextField();
		display.setAlignment(Pos.BASELINE_RIGHT);
		
		VBox calc = new VBox(10);
		calc.setPadding(new Insets(10,10,10,10));
		calc.getChildren().addAll(display,pane);
		
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
			currentState.enterOperand(.5);
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
		
		Scene scene = new Scene(calc, 235, 213);
		primaryStage.setTitle("Calculator");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public static void main (String args[]) {
		Application.launch(args);
	}
		
	/*
	 * at this state, can continue adding to operand1 or add operator
	 */
	public class OperatorState implements CalculatorState {
		boolean decimals = false;
		public void enterOperand(double operand) {
			if(operand!=.5 && !decimals) {
				operand1 = operand1*10 + operand;
				display.setText(operand1%1==0 ? Integer.toString((int)operand1) : Double.toString(operand1));
			} else if (operand == .5 ){
				display.setText(display.getText() + ".");
				decimals = true;
			} else if (!display.getText().equals("0")){
				display.setText(display.getText() + Integer.toString((int)operand));
			}
		}
		
		public void enterOperator(Operator operator) { //THIS IS WHERE THE DECIMAL DIES 
			if(decimals) {
				operand1 = Double.parseDouble(display.getText());
				decimals = false;
			}
			twoOperation = operator;
			display.setText(operand1%1==0 ? Integer.toString((int)operand1) : Double.toString(operand1));
			currentState = twoOperationState;
		}
		
		public void enterOperator(SingleOperator operator) {
			oneOperation = operator;
			operand1 = oneOperation.compute(operand1);
			display.setText(operand1%1==0 ? Integer.toString((int)operand1) : Double.toString(operand1));
//			currentState = excessOperatorState;
		}
		
		//does nothing on purpose 
		public void compute() {
			
		}
		
		public void clear() {
			operand1 = 0.0;
			operand2 = 0.0;
			oneOperation = null;
			display.setText("0");
		}
		
		public void clearEntry() {
			clear();
		}
	}
	
	public class OneOperationState implements CalculatorState {
		public void enterOperand(double operand) {
	
		}
		
		public void enterOperator(Operator operator) {
			compute();
			twoOperation = operator;
			currentState = twoOperationState;
		}
		
		public void enterOperator(SingleOperator operator) {
			compute();
			oneOperation = operator;
		}

		public void compute() {
			operand1 = oneOperation.compute(operand1);
			display.setText(Double.toString(operand1));
//			currentState = excessOperatorState;
		}
		
		public void clear() {
			operand1 = 0.0;
			operand2 = 0.0;
			oneOperation = null;
			display.setText("0");
			currentState = operatorState;

		}
		
		public void clearEntry() {
			operand2 = 0.0;
			display.setText("0");
		}
	}
	
//	public class SecondOperandState implements CalculatorState {
//		boolean decimals;
//		public void enterOperand(double operand) {
//			if(operand!=.5 && !decimals) {
//				operand2 = operand2*10 + operand;
//				display.setText(operand2%1==0 ? Integer.toString((int)operand2) : Double.toString(operand2));
//			} else if (operand == .5 ){
//				display.setText(display.getText() + ".");
//				decimals = true;
//			} else if (!display.getText().equals("0")){
//				display.setText(display.getText() + Integer.toString((int)operand));
//			}
//			
//			
//		}
//		
//		public void enterOperator(Operator operator) {
//			
//		}
//		
//		public void enterOperator(SingleOperator operator) {
//			
//		}
//
//		public void compute() {
//			operand2 = Double.parseDouble(display.getText());
//			currentState = twoOperationState;
//		}
//		
//		public void clear() {
//			operand1 = 0.0;
//			operand2 = 0.0;
//			oneOperation = null;
//			display.setText("0");
//			currentState = operatorState;
//
//		}
//		
//		public void clearEntry() {
//			operand2 = 0.0;
//			display.setText("0");
//		}
//		
//	}
	
	public class TwoOperationState implements CalculatorState {
		boolean decimals;
		public void enterOperand(double operand) {
			if(operand!=.5 && !decimals) {
				operand2 = operand2*10 + operand;
				display.setText(operand2%1==0 ? Integer.toString((int)operand2) : Double.toString(operand2));
			} else if (operand == .5 ){
				display.setText(display.getText() + ".");
				decimals = true;
			} else if (!display.getText().equals("0")){
				display.setText(display.getText() + Integer.toString((int)operand));
			}
		}
		public void enterOperator(Operator operator) {
			if(decimals) {
				operand2 = Double.parseDouble(display.getText());
				decimals = false;
			}
			compute();
			twoOperation = operator;
		}
		
		public void enterOperator(SingleOperator operator) {
			compute();
			oneOperation = operator;
			currentState = oneOperationState;
		}

		public void compute() {
			if(decimals) {
				operand2 = Double.parseDouble(display.getText());
				decimals = false;
			}
			System.out.println(operand1);
			System.out.println(operand2);
			operand1 = twoOperation.compute(operand1, operand2);
			System.out.println("After:" + operand1);
			System.out.println(operand2);
			display.setText(operand1%1==0 ? Integer.toString((int)operand1) : Double.toString(operand1));
			operand2 = 0.0;
			twoOperation = null;
			//currentState = excessOperatorState;
			System.out.println("yo wtf " + currentState);

		}
		
		public void clear() {
			operand1 = 0.0;
			operand2 = 0.0;
			oneOperation = null;
			display.setText("0");
			currentState = operatorState;

		}
		
		public void clearEntry() {
			operand2 = 0.0;
			display.setText("0");
		}
	
	}
	
//	public class ExcessOperatorState implements CalculatorState {
//		public void enterOperand(double operand) {
//			
//		}
//		
//		public void enterOperator(Operator operator) {
//			twoOperation = operator;
//			System.out.println("we out here");
//			currentState = twoOperationState;
//		}
//		
//		public void enterOperator(SingleOperator operator) {
//			oneOperation = operator;
//			operand1 = oneOperation.compute(operand1);
//			display.setText(Double.toString(operand1));
//		}
//		
//		public void compute() {
//			
//		}
//		
//		public void clear() {
//			operand1 = 0.0;
//			operand2 = 0.0;
//			oneOperation = null;
//			display.setText("0");
//			currentState = operatorState;
//
//		}
//		
//		public void clearEntry() {
//			
//		}
//	}
}

