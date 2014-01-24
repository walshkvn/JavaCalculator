package ie.ait.mase.calcproject;

import java.util.Queue;
import java.util.Stack;

public class ReversePolishParser {
	
	private Calculator calc;
	private Stack<String> numberStack = new Stack<String>();
	
	// Operation Constants
	private final char PLUS = '+', SUBTRACTION = '-'; // add more as add functionality
	
	// Constructor
	public ReversePolishParser(Calculator calc) {
		this.calc = calc; // need the calculator to call the operations from.
	}
	
	//Pre: The problem to solve must be in reverse polish form
	//Post: Returns the result of the problem as a String
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
				String num2 = numberStack.pop();
				String num1 = numberStack.pop();
				
				// calculate result
				numberStack.add(String.valueOf(calculateResult(num1, num2, nextToken)));
				//numberStack.add(String.valueOf(Integer.parseInt(num1) + Integer.parseInt(num2)));
			}
		}
		
		// return top of stack as result
		return numberStack.pop();
	}
	
	//Pre: 
	//Post: Returns the result of the calculator as a double
	private double calculateResult(String num1, String num2, String nextToken) {
		double result = 0.0;
		
		switch (nextToken.charAt(0)) {
		case PLUS:
			result = calc.add(Double.valueOf(num1), Double.valueOf(num2));
			break;
		case SUBTRACTION:
			result = calc.subtract(Double.valueOf(num1), Double.valueOf(num2));
			break;
		default:
			System.out.println("This operation has not yet been implemented: " + nextToken);
			break;
		}
		
		return result;
	}

	private boolean isOperator(String nextToken) {
		
		boolean result = false;
		
		// Check to see if this is one of our tokens
		switch (nextToken.charAt(0)) {
		default:
			result = false;
			break;
		case PLUS: case SUBTRACTION:
			result = true;
			break;
		}
		
		return result;
	}

}
