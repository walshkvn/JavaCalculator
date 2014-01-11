package ie.ait.mase.calcproject;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.Before;
import org.junit.Test;

public class ReversePolishCalculatorTest {
	
	ReversePolishCalculator rpCalc;
	Queue<String> problem;
	
	@Before
	public void setUp() throws Exception {
		rpCalc = new ReversePolishCalculator();
		problem = new LinkedList<>();
	}

	@Test
	public void testSimpleAddition() {
		problem.add("3");
		problem.add("5");
		problem.add("+");
		assertEquals("8", rpCalc.calcProblem(problem));
	}

}
