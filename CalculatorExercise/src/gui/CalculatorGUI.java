package gui;

import java.util.ArrayList;

import javafx.application.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class CalculatorGUI extends Application{
	TextField display;
	
	CalculatorState operandState = new OperandState();
	CalculatorState oneOperatorState = new OneInputOperatorState();
	CalculatorState twoOperatorState = new TwoInputOperatorState();
	CalculatorState excessOperatorState = new ExcessOperatorState();
	
	CalculatorState currentState = operandState;
	
	double operand1 = 0.0;
	double operand2 = 0.0;
	Operator operation;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		GridPane pane = new GridPane();
		pane.setVgap(5);
		pane.setHgap(5);
		pane.setPadding(new Insets(10,10,10,10));
		
		/*create all the buttons*/
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
		
		display = new TextField();
		VBox calc = new VBox();
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
		
		inverse.setOnAction(e -> {
			currentState.enterOperator(Operator.INVERSE);
		});
		
		sqrt.setOnAction(e -> {
			currentState.enterOperator(Operator.SQRT);
		});
		
		clear.setOnAction(e -> {
			currentState.clear();
		});
		
		clearE.setOnAction(e -> {
			currentState.clearEntry();
		});
		
		Scene scene = new Scene(calc, 400, 605);
		primaryStage.setTitle("Calculator");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public static void main (String args[]) {
		Application.launch(args);
	}
	
	public class OperandState implements CalculatorState {
		public void enterOperand(double operand) {
			operand1 = operand1*10 +  operand;
			display.setText(Double.toString(operand1));
		}
		
		public void enterOperator(Operator operator) {
			operation = operator;
			//currentState = operator.getState();
			display.setText(operand1 + operation.toString() + operand2);

		}
		
		public void compute() {
			
		}
		
		public void clear() {
			operand1 = 0.0;
			operand2 = 0.0;
			operation = null;
		}
		
		public void clearEntry() {
			operand1 = 0.0;
		}
		
	}
	
	public class OneInputOperatorState implements CalculatorState {
		public void enterOperand(double operand) {
			
		}
		
		public void enterOperator(Operator operator) {
			
		}
		
		public void compute() {
			
		}
		
		public void clear() {
			operand1 = 0.0;
			operand2 = 0.0;
			operation = null;
		}
		
		public void clearEntry() {
			display.setText("0.0");
		}
	}
	
	public class TwoInputOperatorState implements CalculatorState {
		public void enterOperand(double operand) {
			
		}
		
		public void enterOperator(Operator operator) {
			
		}
		
		public void compute() {
			
		}
		
		public void clear() {
			operand1 = 0.0;
			operand2 = 0.0;
			operation = null;
		}
		
		public void clearEntry() {
			display.setText("0.0");
		}
	}
	
	public class SecondOperandState implements CalculatorState {
		public void enterOperand(double operand) {
			
		}
		
		public void enterOperator(Operator operator) {
			
		}
		
		public void compute() {
			
		}
		
		public void clear() {
			operand1 = 0.0;
			operand2 = 0.0;
			operation = null;
		}
		
		public void clearEntry() {
			operand2 = 0.0;
		}
		
	}
	
	public class ExcessOperatorState implements CalculatorState {
		public void enterOperand(double operand) {
			
		}
		
		public void enterOperator(Operator operator) {
			
		}
		
		public void compute() {
			
		}
		
		public void clear() {
			operand1 = 0.0;
			operand2 = 0.0;
			operation = null;
		}
		
		public void clearEntry() {
			
		}
	}
}

