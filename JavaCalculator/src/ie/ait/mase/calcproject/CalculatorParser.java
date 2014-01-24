package ie.ait.mase.calcproject;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class CalculatorParser {
	
	private final char ZERO = 47, NINE = 57, PLUS = 43, MINUS = 45;
	
	private Queue<String> calcQueue = new LinkedList<String>();
	private Stack<String> calcStack = new Stack();
	
	public Queue<String> parse(String calcToParse) {
		
		String reversePolish = null;
		
		// while there is a token read it
		for (int i = 0; i < calcToParse.length(); i++) {
			char nextToken = calcToParse.charAt(i);
			
			// if the token is a number save to the queue
			if (nextToken >= ZERO && nextToken <= NINE) {
				addToQueue(String.valueOf(nextToken));
			} else if (isOperator(nextToken)) {
				addToStack(String.valueOf(nextToken));
			}
			
		}
		
		// Put the elements from the stack into the queue in the correct order
		for (int i = 0; i < calcStack.size(); i++) {
			addToQueue(calcStack.pop());
		}
		
		return calcQueue;
		
	}
	
	private boolean isOperator(char nextToken) {
		boolean result = false;
		
		switch (nextToken) {
		default: 
			result = false;
			break;
		case PLUS: case MINUS:
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
