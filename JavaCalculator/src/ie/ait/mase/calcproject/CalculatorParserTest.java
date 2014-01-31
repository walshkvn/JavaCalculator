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
	
	@Test
	public void testTwoDigitInteger() {
		result = parser.parse("15");
		tempQueue = new LinkedList<String>(result);
		element = getElementAtIndex(0);
		assertEquals("15", element);
	}
	
	@Test
	public void testNumberWithDecimalPlaces() {
		result = parser.parse("1.001");
		tempQueue = new LinkedList<String>(result);
		element = getElementAtIndex(0);
		assertEquals("1.001", element);
	}
	
	@Test
	public void testWithTwo1DigitNumbersSeperatedByOperatorANDIncludeSpaces() {
		result = parser.parse("3 + 5");
		tempQueue = new LinkedList<String>(result);
		element = getElementAtIndex(0);
		assertEquals("3", element);
		
		tempQueue = new LinkedList<String>(result); // need to reset queue as elements may already have been removed
		element = getElementAtIndex(1);
		assertEquals("5", element);
		
		tempQueue = new LinkedList<String>(result); // need to reset queue as elements may already have been removed
		element = getElementAtIndex(2);
		assertEquals("+", element);
	}
	
	@Test
	public void testSimpleAdditionWithParentheses() {
		result = parser.parse("3 + (5 + 2)");
		tempQueue = new LinkedList<String>(result); // need to reset queue as elements may already have been removed
		assertEquals("3", getElementAtIndex(0));
		
		tempQueue = new LinkedList<String>(result); // need to reset queue as elements may already have been removed
		assertEquals("5", getElementAtIndex(1));
		
		tempQueue = new LinkedList<String>(result); // need to reset queue as elements may already have been removed
		assertEquals("2", getElementAtIndex(2));
		
		tempQueue = new LinkedList<String>(result); // need to reset queue as elements may already have been removed
		assertEquals("+", getElementAtIndex(3));
		
		tempQueue = new LinkedList<String>(result); // need to reset queue as elements may already have been removed
		assertEquals("+", getElementAtIndex(4));
	}
	
	@Test
	public void testSimpleAdditionAndSubtractionWithParentheses() {
		result = parser.parse("3 + (5 - 2)");
		tempQueue = new LinkedList<String>(result); // need to reset queue as elements may already have been removed
		assertEquals("3", getElementAtIndex(0));
		
		tempQueue = new LinkedList<String>(result); // need to reset queue as elements may already have been removed
		assertEquals("5", getElementAtIndex(1));
		
		tempQueue = new LinkedList<String>(result); // need to reset queue as elements may already have been removed
		assertEquals("2", getElementAtIndex(2));
		
		tempQueue = new LinkedList<String>(result); // need to reset queue as elements may already have been removed
		assertEquals("-", getElementAtIndex(3));
		
		tempQueue = new LinkedList<String>(result); // need to reset queue as elements may already have been removed
		assertEquals("+", getElementAtIndex(4));
	}
	
	@Test
	public void testAdvancedAdditionAndSubtractionWithParentheses() {
		result = parser.parse("3+(5-4)+(5-1)");
		tempQueue = new LinkedList<String>(result); // need to reset queue as elements may already have been removed
		assertEquals("3", getElementAtIndex(0));
		
		tempQueue = new LinkedList<String>(result); // need to reset queue as elements may already have been removed
		assertEquals("5", getElementAtIndex(1));
		
		tempQueue = new LinkedList<String>(result); // need to reset queue as elements may already have been removed
		assertEquals("4", getElementAtIndex(2));
		
		tempQueue = new LinkedList<String>(result); // need to reset queue as elements may already have been removed
		assertEquals("-", getElementAtIndex(3));
		
		tempQueue = new LinkedList<String>(result); // need to reset queue as elements may already have been removed
		assertEquals("+", getElementAtIndex(4));
		
		tempQueue = new LinkedList<String>(result); // need to reset queue as elements may already have been removed
		assertEquals("5", getElementAtIndex(5));
		
		tempQueue = new LinkedList<String>(result); // need to reset queue as elements may already have been removed
		assertEquals("1", getElementAtIndex(6));
		
		tempQueue = new LinkedList<String>(result); // need to reset queue as elements may already have been removed
		assertEquals("-", getElementAtIndex(7));
		
		tempQueue = new LinkedList<String>(result); // need to reset queue as elements may already have been removed
		assertEquals("+", getElementAtIndex(8));
		
	}
	
	@Test
	public void testAdditionandSubtractionWithoutParentheses() {
		result = parser.parse("3+5-4");
		tempQueue = new LinkedList<String>(result); // need to reset queue as elements may already have been removed
		assertEquals("3", getElementAtIndex(0));
		
		tempQueue = new LinkedList<String>(result); // need to reset queue as elements may already have been removed
		assertEquals("5", getElementAtIndex(1));
		
		tempQueue = new LinkedList<String>(result); // need to reset queue as elements may already have been removed
		assertEquals("4", getElementAtIndex(2));
		
		tempQueue = new LinkedList<String>(result); // need to reset queue as elements may already have been removed
		assertEquals("-", getElementAtIndex(3));
		
		tempQueue = new LinkedList<String>(result); // need to reset queue as elements may already have been removed
		assertEquals("+", getElementAtIndex(4));
	}
	
	
	// other methods:
	private String getElementAtIndex(int index) {
		for (int j = 0; j < index; j++) {
			tempQueue.poll();
		}
		return tempQueue.peek();
	}
}
