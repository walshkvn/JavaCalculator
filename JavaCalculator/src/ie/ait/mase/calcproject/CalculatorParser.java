package ie.ait.mase.calcproject;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class CalculatorParser {
	
	private final char ZERO = 47, NINE = 57, PLUS = '+', MINUS = '-', DOT = '.',
			MULTIPLY='*', DIVIDE='/', LEFT_BRACKET='(', RIGHT_BRACKET=')'; // add more as more functionality added
	
	private Queue<String> calcQueue = new LinkedList<String>();
	private Stack<String> calcStack = new Stack();
	
	public Queue<String> parse(String calcToParse) {
		
		String reversePolish = null;
		
		// while there is a token read it
		String nextNumber = ""; // used to put multiple digits together to form a full number
		for (int i = 0; i < calcToParse.length(); i++) {
			char nextToken = calcToParse.charAt(i);
			
			// if the token is a number save to the queue
			if ((nextToken >= ZERO && nextToken <= NINE) || nextToken == DOT) {
				// add the nextToken to the temp number holder: nextNumber
				nextNumber += nextToken;
			} else if (isOperator(nextToken)) {
				addToStack(String.valueOf(nextToken));
				
				// add the next number to the Queue as we have reached an operator, i.e. the end of the number
				if(!nextNumber.equalsIgnoreCase("")) {
					addToQueue(String.valueOf(nextNumber));
					nextNumber=""; // reset for the next number
				}
			} else if (nextToken == LEFT_BRACKET) {
				// add the next number to the Queue as we have reached an operator, i.e. the end of the number
				if(!nextNumber.equalsIgnoreCase("")) {
					addToQueue(String.valueOf(nextNumber));
					nextNumber=""; // reset for the next number
				}
				
				// add to stack
				addToStack(String.valueOf(nextToken));
			} else if (nextToken == RIGHT_BRACKET) {
				// add the next number to the Queue as we have reached an operator, i.e. the end of the number
				if(!nextNumber.equalsIgnoreCase("")) {
					addToQueue(String.valueOf(nextNumber));
					nextNumber=""; // reset for the next number
				}
					
				
				while(!calcStack.isEmpty() && !calcStack.peek().equalsIgnoreCase("(")) {
					addToQueue(calcStack.pop());
				}
				calcStack.pop(); // need to get rid of the left parentheses
				if(!calcStack.isEmpty())
					addToQueue(calcStack.pop()); // add the operator associated with the parentheses
			}
			
		}
		
		// problems don't usually end in operators so need to add the last number to the queue
		if (!nextNumber.equalsIgnoreCase(""))
			addToQueue(String.valueOf(nextNumber));
					
		
		// Put the elements from the stack into the queue in the correct order
		for (int i = 0; i < calcStack.size(); i++) {
			addToQueue(calcStack.pop());
		}
		
		return calcQueue;
		
	}

	// Check if a character is an operator +,-,*, etc
	private boolean isOperator(char nextToken) {
		boolean result = false;
		
		switch (nextToken) {
		default: 
			result = false;
			break;
		case PLUS: case MINUS: case MULTIPLY: case DIVIDE:
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

}
