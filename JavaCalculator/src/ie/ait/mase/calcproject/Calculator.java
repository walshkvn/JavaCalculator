package ie.ait.mase.calcproject;

import java.math.BigDecimal;
import java.util.Queue;

public class Calculator {
	
	
	public String calculate(String problem) {
		// parse string into its component parts and return it in Reverse Polish
		// form
		CalculatorParser parser = new CalculatorParser();
		Queue<String> problemRP = parser.parse(problem);

		// calculate the result of the problem in reverse polish notation
		String result = calcProblemUsingReversePolish(problemRP);

		return result;
	}

	public String calculate(String problem, boolean radians) {
		// parse string into its component parts and return it in Reverse Polish
		// form
		CalculatorParser parser = new CalculatorParser();
		Queue<String> problemRP = parser.parse(problem);

		// calculate the result of the problem in reverse polish notation
		String result = calcProblemUsingReversePolish(problemRP);

		return result;
	}
	
	private String calcProblemUsingReversePolish(Queue<String> problemRP) {
		// Create the Reverse Polish Calculator to use
		ReversePolishParser rvCalc = new ReversePolishParser(this);

		// Feed the calculator the problem
		String problemResult = rvCalc.calcProblem(problemRP);

		return problemResult;
	}

	// Calculate an addition
	public double add(double num1, double num2) {
		return num1 + num2;
	}

	// Calculate an subtraction
	public double subtract(double num1, double num2) {
		BigDecimal num1Bg = new BigDecimal(Double.toString(num1));
		BigDecimal num2Bg = new BigDecimal(Double.toString(num2));

		return num1Bg.subtract(num2Bg).doubleValue();
	}

	public double multiply(Double num1, Double num2) {
		return num1 * num2;
	}

	public double divide(Double num1, Double num2) {
		return num1 / num2;
	}

	public double calcCos(Double num1) {
		return Math.cos(num1);
	}
}
