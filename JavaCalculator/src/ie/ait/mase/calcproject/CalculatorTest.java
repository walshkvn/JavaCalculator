package ie.ait.mase.calcproject;

import static org.junit.Assert.*;

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
		assertEquals(8, calc.calculate("3+5"));
	}
	
	@Test
	public void testSimpleAdditionII() {
		assertEquals(10, calc.calculate("5+5"));
	}

}
