package ie.ait.mase.calcproject;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class CalculatorParser {
	private final char ZERO = 48, NINE = 57, PLUS = '+', MINUS = '-',
			DOT = '.', MULTIPLY = '*', DIVIDE = '/', LEFT_BRACKET = '(',
			RIGHT_BRACKET = ')', POWER = '^', ROOT = '√'; // add more as more functionality
												// added

	private Queue<String> calcQueue = new LinkedList<String>();
	private Stack<String> calcStack = new Stack();
	private boolean precedence = false;
	private boolean otherOperation = false;
	private boolean powerOperation = false;

	public Queue<String> parse(String calcToParse) {

		String reversePolish = null;

		String function = ""; // Used to store the values of all non number or
								// operand things
								// e.g cos, sin, etc.

		// while there is a token read it
		String nextNumber = ""; // used to put multiple digits together to form
								// a full number
		for (int i = 0; i < calcToParse.length(); i++) {
			char nextToken = calcToParse.charAt(i);

			// if the token is a number save to the queue
			breakLoop:	//Used for negative numbers
			if ((nextToken >= ZERO && nextToken <= NINE) || nextToken == DOT) {
				// add the nextToken to the temp number holder: nextNumber
				nextNumber += nextToken;
//
//				if (i >= nextNumber.length()) {
//					if (calcStack.peek().equals(String.valueOf(MULTIPLY))
//							|| calcStack.peek().equals(String.valueOf(DIVIDE))
//							|| calcStack.peek().equals(String.valueOf(POWER))
//							|| calcStack.peek().equals(String.valueOf(ROOT)))
//						precedence = true;
//				}

			} else if (isOperator(nextToken)) {
				//checks if first thing in string is an operator. or if a negative operator immediately follows a left bracket 
				if((i==0 && (nextToken == '-')) || ((calcToParse.charAt(i-1)=='(')&&(nextToken == '-'))){
					nextNumber+=nextToken;
					break breakLoop;
				}
				
				// put the nextNumber to the Queue as we have finished reading in numbers
				if (!nextNumber.equalsIgnoreCase("")) {
					addToQueue(String.valueOf(nextNumber));

					nextNumber = ""; // reset for the next number
				}
				
				if (!calcStack.isEmpty()) {
					switch (calcStack.peek()) {
					case "^": case "√":
						precedence = true;
					case "*": case "/":
						if (nextToken == '+' || nextToken == '-') {
							precedence = true;
						}
					}
				}
				
				if (!calcStack.isEmpty() && (precedence && !calcStack.peek().equals("("))) {
					// adds high precedence operands to queue
					addToQueue(calcStack.pop()); // emptys queue if the next
													// operator is a minus or
													// plus
					precedence = false; // reset precedence
					if (nextToken == MINUS || nextToken == PLUS) {
						emptyStack();
					}

				}

				addToStack(String.valueOf(nextToken));

				// add the next number to the Queue as we have reached an
				// operator, i.e. the end of the number
			} else if (nextToken == LEFT_BRACKET) {
				// add the next number to the Queue as we have reached an
				// operator, i.e. the end of the number
				if (!nextNumber.equalsIgnoreCase("")) {
					addToQueue(String.valueOf(nextNumber));
					nextNumber = ""; // reset for the next number
				}

				// add to stack
				addToStack(String.valueOf(nextToken));
			} else if (nextToken == RIGHT_BRACKET) {
				// add the next number to the Queue as we have reached an
				// operator, i.e. the end of the number
				if (!nextNumber.equalsIgnoreCase("")) {
					addToQueue(String.valueOf(nextNumber));
					nextNumber = ""; // reset for the next number
				}

				while (!calcStack.isEmpty()
						&& !calcStack.peek().equalsIgnoreCase("(")) {
					addToQueue(calcStack.pop());
				}
				if(!calcStack.isEmpty())
					calcStack.pop(); // need to get rid of the left parentheses
				
			} else if ((nextToken < ZERO || nextToken > NINE)
					&& (nextToken != MULTIPLY || nextToken != MINUS
							|| nextToken != PLUS || nextToken != DIVIDE)) {
				function += nextToken;

				switch (function.toLowerCase()) {
					case "sin":
					case "cos":
					case "tan":
					case "ln":
					case "log":
					case "exp":
						otherOperation = true;
						System.out.println(function);
						addToStack(function);
						function = "";
						break;

				}

			}

		}

		// problems don't usually end in operators so need to add the last
		// number to the queue
		if (!nextNumber.equalsIgnoreCase(""))
			addToQueue(String.valueOf(nextNumber));

		// Put the elements from the stack into the queue in the correct order
		int stackSize = calcStack.size();
		for (int i = 0; i < stackSize; i++) {
			addToQueue(calcStack.pop());
		}
		for (String i : calcQueue)
			System.out.print(i);

		return calcQueue;

	}

	// Check if a character is an operator +,-,*, etc
	private boolean isOperator(char nextToken) {
		boolean result = false;

		switch (nextToken) {
		default:
			result = false;
			break;
		case PLUS:
		case MINUS:
		case MULTIPLY:
		case DIVIDE:
		case POWER:
		case ROOT:
			result = true;
			break;
		}

		return result;
	}

	private void addToQueue(String nextElement) {
		calcQueue.add(nextElement);
	}

	private void addToStack(String nextElement) {
		calcStack.push(nextElement);
	}

	public void emptyStack() {
		int stackLength = calcStack.size();
		for (int i = 0; i < stackLength; i++) {
			if(calcStack.peek().equalsIgnoreCase("("))
					break;
			else
				addToQueue(calcStack.pop());
		}

	}
}
