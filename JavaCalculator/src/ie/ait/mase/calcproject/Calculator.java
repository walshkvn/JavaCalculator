package ie.ait.mase.calcproject;

public class Calculator {
	
	public String calculate(String problem) {
		
		//parse string into its component parts and return it in Reverse Polish form
		CalculatorParser parser = new CalculatorParser();
		String problemRP = parser.parse(problem);
		
		// calculate the result of the problem in reverse polish notation
		String result = calcProblemUsingShuntingYard(problemRP);
		
		return result;
	}

	private String calcProblemUsingShuntingYard(String problemRP) {
		
		// 
		
		return null;
	}

}
