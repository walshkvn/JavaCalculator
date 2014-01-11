package ie.ait.mase.calcproject;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.Before;
import org.junit.Test;

public class CalculatorParserTest {
	
	CalculatorParser parser;
	Queue<String> result, tempQueue;
	String element;
	
	@Before
	public void setUp() throws Exception {
		parser = new CalculatorParser();
		result = new LinkedList<String>();
	}
	
	@Test
	public void test1DigitInteger() {
		result = parser.parse("3");
		tempQueue = new LinkedList<String>(result);
		element = getElementAtIndex(0);
		assertEquals("3", element);
	}

	@Test
	public void testQueueWithTwo1DigitNumbersSeperatedByOperator() {
		result = parser.parse("3+5");
		tempQueue = new LinkedList<String>(result);
		element = getElementAtIndex(0);
		assertEquals("3", element);
		
		tempQueue = new LinkedList<String>(result);
		element = getElementAtIndex(1);
		assertEquals("5", element);
	}
	
	@Test
	public void testStackWithTwo1DigitNumbersSeperatedByOperator() {
		result = parser.parse("3+5");
		tempQueue = new LinkedList<String>(result);
		element = getElementAtIndex(2);
		assertEquals("+", element);
	}

	private String getElementAtIndex(int index) {
		for (int j = 0; j < index; j++) {
			tempQueue.poll();
		}
		return tempQueue.peek();
	}
}
