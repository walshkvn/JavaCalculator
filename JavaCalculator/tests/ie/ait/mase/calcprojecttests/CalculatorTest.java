package ie.ait.mase.calcprojecttests;

import static org.junit.Assert.*;
import ie.ait.mase.calcproject.Calculator;

import org.junit.Before;
import org.junit.Test;

public class CalculatorTest {

	Calculator calc; 
	
	@Before
	public void setUp() throws Exception {
		calc = new Calculator();
	}
	
	@Test
	public void testSimpleAddition() {
		assertEquals("8.0", calc.calculate("3+5"));
	}
	
	@Test
	public void testSimpleAdditionII() {
		assertEquals("10.0", calc.calculate("5+5"));
	}
	
	@Test
	public void testSimpleSubtraction() {
		assertEquals("-2.0", calc.calculate("3-5"));
	}
	
	public void testSimpleMultiplication() {
		assertEquals("6.0", calc.calculate("4*3"));
	}
	
	@Test
	public void testSimpleAdditionWithSpaces() {
		assertEquals("8.0", calc.calculate("3 + 5"));
	}
	
	@Test
	public void testSimpleAdditionWithParentheses() {
		assertEquals("15.0", calc.calculate("3 + (10 + 2)"));
	}
	
	@Test
	public void testSimpleAdditionAndSubtractionWithParentheses() {
		assertEquals("4.0", calc.calculate("3 + (5 - 4)"));
	}
	
	@Test
	public void testAdvancedAdditionAndSubtractionWithParentheses() {
		assertEquals("8.0", calc.calculate("3 + (5 - 4) + (5-1)"));
	}

	@Test
	public void testAdvancedAdditionAndSubtractionWithoutParentheses() {
		assertEquals("4.0", calc.calculate("3 + 5 - 4"));
	}
	
}
