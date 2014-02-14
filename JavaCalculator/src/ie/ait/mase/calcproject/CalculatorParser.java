package ie.ait.mase.calcproject;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class CalculatorParser {
	private final char ZERO = 48, NINE = 57, PLUS = '+', MINUS = '-',
			DOT = '.', MULTIPLY = '*', DIVIDE = '/', LEFT_BRACKET = '(',
			RIGHT_BRACKET = ')', POWER = '^', ROOT = '√', BLOG = 'v'; // add more as more functionality
												// added

	private Queue<String> calcQueue = new LinkedList<String>();
	private Stack<String> calcStack = new Stack();
	private boolean precedence = false;
	private int otherOperation = 0;
	private boolean powerOperation = false;
	private final double π = Math.PI;
	private final double e = Math.E;
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
			if ((nextToken >= ZERO && nextToken <= NINE) || nextToken == DOT || nextToken == 'π' || nextToken == 'e' ) {
				// add the nextToken to the temp number holder: nextNumber
				nextNumber += nextToken;
				
			} else if (isOperator(nextToken)|| (function.equalsIgnoreCase("blo") && nextToken=='g')) {
				if(nextToken=='g'){
					nextToken = BLOG;
					function = "";
					otherOperation++;
				}
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
					case "^": case "√": case "v":
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
				if(otherOperation>0 && !isfuntion(calcStack.peek())) // need to make sure that the ( is belonging to another function.
					otherOperation--;
				
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

				if(otherOperation>0) {// need to pop off function belonging to the bracket
					addToQueue(calcStack.pop());
					otherOperation--;
				}
				
			} else if ((nextToken < ZERO || nextToken > NINE)
					&& (nextToken != MULTIPLY || nextToken != MINUS
							|| nextToken != PLUS || nextToken != DIVIDE)) {
				function += nextToken;
				
				if (isfuntion(function)) {
					otherOperation+=1;
					System.out.println(function);
					addToStack(function.toLowerCase());
					function = "";
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

	private boolean isfuntion(String function) {
		switch (function.toLowerCase()) {
			case "sin":
			case "cos":
			case "tan":
			case "ln":
			case "log":
			case "exp":
			case "v":
				return true;
			default:
				return false;

		}
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