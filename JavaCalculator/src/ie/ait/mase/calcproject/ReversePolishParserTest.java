package ie.ait.mase.calcproject;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.Before;
import org.junit.Test;

public class ReversePolishParserTest {
	
	ReversePolishParser rpParser;
	Queue<String> problem;
	
	@Before
	public void setUp() throws Exception {
		Calculator calc = new Calculator();
		rpParser = new ReversePolishParser(calc);
		problem = new LinkedList<>();
	}

	@Test
	public void testSimpleAddition() {
		problem.add("3");
		problem.add("5");
		problem.add("+");
		assertEquals("8.0", rpParser.calcProblem(problem));
	}
	
	@Test
	public void testSimpleSubtraction() {
		problem.add("3");
		problem.add("5");
		problem.add("-");
		assertEquals("-2.0", rpParser.calcProblem(problem));
	}
	
	@Test
	public void testDoubleSubtraction() {
		problem.add("3.2");
		problem.add("5.8");
		problem.add("-");
		assertEquals("-2.6", rpParser.calcProblem(problem));
	}
	
	@Test
	public void testSimpleMultiplication() {
		problem.add("3");
		problem.add("2");
		problem.add("*");
		assertEquals("6.0", rpParser.calcProblem(problem));
	}
	
	@Test
	public void testDoubleMultiplication() {
		problem.add("6.1234");
		problem.add("1.25");
		problem.add("*");
		assertEquals("7.65425", rpParser.calcProblem(problem));
	}
	
	@Test
	public void testSimpleDivision() {
		problem.add("10");
		problem.add("2");
		problem.add("/");
		assertEquals("5.0", rpParser.calcProblem(problem));
	}
	
	@Test
	public void testDoubleDivision() {
		problem.add("10.5");
		problem.add("2.5");
		problem.add("/");
		assertEquals("4.2", rpParser.calcProblem(problem));
	}

}
