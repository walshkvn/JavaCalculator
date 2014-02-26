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
	
	@Test
	public void testSimpleMultiplication() {
		assertEquals("12.0", calc.calculate("4*3"));
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
	
	@Test
	public void testMultiplication() {
		assertEquals("12.0", calc.calculate("3*4"));
	}
	
	@Test
	public void testMultiplicationAndAddition() {
		assertEquals("23.0", calc.calculate("3 + 5 * 4"));
	}
	
	@Test
	public void testDivision() {
		assertEquals("4.0", calc.calculate("12/3"));
	}
	
	@Test
	public void testDivisionWithSubtraction () {
		assertEquals("96.0", calc.calculate("100-12/3"));
	}
	
	@Test
	public void testDoubleBrackets() {
		assertEquals("9.0", calc.calculate("((4+5))"));
	}
	
	@Test
	public void testCosSquareRootMultiplySquared() {
		assertEquals("0.9111991345515644", calc.calculate("cos(50+2\u221A10*2^2)/log(12)", true));
	}
	
	@Test
	public void testCosSinTanInDeg() {
		assertEquals("0.09661109738602398", calc.calculate("cos(50)*sin(45)*tan(12)", false));
	}
	
	@Test
	public void testCosSinTanInRad() {
		assertEquals("-0.5221001330342304", calc.calculate("cos(50)*sin(45)*tan(12)", true));
	}
	
	@Test
	public void testLogBase2() {
		assertEquals("3.3219280948873626", calc.calculate("2blog10", true));
	}
	
	@Test
	public void testNegativeValueFirst() {
		assertEquals("-5.0", calc.calculate("-2-3"));
	}
	
	@Test
	public void testPrecedence() {
		assertEquals("0.9769214730400709", calc.calculate("(cos(4+5^2/3))"));
	}
}
