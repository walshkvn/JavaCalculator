package ie.ait.mase.calcproject;

import java.util.Queue;
import java.util.Stack;

public class ReversePolishParser {

	private Calculator calc;
	private Stack<String> numberStack = new Stack<String>();

	// Operation Constants
	private final String PLUS = "+", SUBTRACTION = "-", MULTIPLICATION = "*",
			DIVISION = "/", POWER = "^", ROOT = "\u221A", SIN="sin"; // add more as add functionality

	// Constructor
	public ReversePolishParser(Calculator calc) {
		this.calc = calc; // need the calculator to call the operations from.
	}

	// Pre: The problem to solve must be in reverse polish form
	// Post: Returns the result of the problem as a String
	public String calcProblem(Queue<String> problemRPForm) {

		// Scan the given queue getting each element
		int queueSize = problemRPForm.size();
		for (int i = 0; i < queueSize; i++) {
			// get the next element on the queue
			String nextToken = problemRPForm.poll();
			// if number then push onto stack
			if (!isOperator(nextToken))
				numberStack.add(nextToken);
			else {
				if(nextToken.length()>1){
					String num1 = numberStack.pop();
					numberStack.add(String.valueOf(calculateResult(num1,"1",
							nextToken)));
				}else{
					String num2 = numberStack.pop();
					String num1 = numberStack.pop();
					numberStack.add(String.valueOf(calculateResult(num1, num2,
							nextToken)));
				}
				// calculate result
				

			}
		}

		// return top of stack as result
		return numberStack.pop();
	}

	// Pre:
	// Post: Returns the result of the calculator as a double
	private double calculateResult(String num1, String num2, String nextToken) {
		double result = 0.0;

		switch (nextToken) {
		case "+":
			result = calc.add(Double.valueOf(num1), Double.valueOf(num2));
			break;
		case "-":
			result = calc.subtract(Double.valueOf(num1), Double.valueOf(num2));
			break;
		case "*":
			result = calc.multiply(Double.valueOf(num1), Double.valueOf(num2));
			break;
		case "/":
			result = calc.divide(Double.valueOf(num1), Double.valueOf(num2));
			break;
		case "^":
			result = Math.pow(Double.valueOf(num1),Double.valueOf(num2));
			break;
		case "\u221A":
			result = Math.pow(Double.valueOf(num2), 1 / Double.valueOf(num1));
			break;
		case "sin":
			result = Math.sin(Double.valueOf(num1));
		default:
			System.out.println("This operation has not yet been implemented: "
					+ nextToken);
			break;
		}

		return result;
	}

	private boolean isOperator(String nextToken) {

		boolean result = false;

		// Check to see if this is one of our tokens
		switch (nextToken) {
		default:
			result = false;
			break;
		case PLUS:
		case SUBTRACTION:
		case MULTIPLICATION:
		case DIVISION:
		case POWER:
		case ROOT:
		case SIN:
			result = true;
			break;
		}

		return result;
	}

}