package ie.ait.mase.calcproject;

import java.util.Queue;
import java.util.Stack;

public class ReversePolishCalculator {

	private final char PLUS = '+';
	
	private Stack<String> numberStack = new Stack<String>();
	
	public Object calcProblem(Queue<String> problemRV) {
		
		// Scan the given queue getting each element
		int queueSize = problemRV.size();
		for (int i = 0; i < queueSize; i++) {
			// get the next element on the queue
			String nextToken = problemRV.poll();
			// if number then push onto stack
			if (nextToken.charAt(0) != PLUS) {
				numberStack.add(nextToken);
			} else {
				String num1 = numberStack.pop();
				String num2 = numberStack.pop();
				// calculate result
				//numberStack.add(result);
				
				numberStack.add(String.valueOf(Integer.parseInt(num1) + Integer.parseInt(num2)));
			}
		}
		
		// return top of stack as result
		return numberStack.pop();
	}

}
