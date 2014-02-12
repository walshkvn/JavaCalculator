package ie.ait.mase.calcprojecttests;

import static org.junit.Assert.*;
import ie.ait.mase.calcproject.CalculatorUI;

import java.awt.Component;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.junit.Before;
import org.junit.Test;

public class CalculatorUITest {

	CalculatorUI uiCalc;
	JTextField field;
	JLabel degRadLabel;
	
	@Before
	public void setUp() throws Exception {
		uiCalc = new CalculatorUI();
		field = (JTextField)getChildNamed(uiCalc, "display");
		degRadLabel = (JLabel)getChildNamed(uiCalc, "d/r toggle");
	}

	@Test
	public void testNumber1Button() {
		// get the different components
		String numName = "0";
		
		JButton button = (JButton)getChildNamed(uiCalc, numName);
		assertNotNull(button);
		
		assertNotNull(field);
		
		// click the required button
		button.doClick();
		assertEquals(numName, field.getText());
	}
	
	@Test
	public void testNumberButtons() {
		//check the field there
		assertNotNull(field);
		
		// get the different components
		for (int i = 1; i < 10; i++) {
			String numName = "" + i;
			
			JButton button = (JButton)getChildNamed(uiCalc, numName);
			assertNotNull(button);
			
			// click the required button
			button.doClick();
			assertEquals(numName, field.getText());
			field.setText("");
		}
	}
	
	@Test
	public void testDotOperatorButton() {
		String buttonName = ".";

		testOperatorDisplayButtons(buttonName, buttonName);
	}
	
	@Test
	public void testPlusOperatorButton() {
		String buttonName = "+";

		testOperatorDisplayButtons(buttonName, buttonName);
	}
	
	@Test
	public void testMinusOperatorButton() {
		String buttonName = "-";

		testOperatorDisplayButtons(buttonName, buttonName);
	}
	
	@Test
	public void testMultiplyOperatorButton() {
		String buttonName = "*";

		testOperatorDisplayButtons(buttonName, buttonName);
	}
	
	@Test
	public void testDivideOperatorButton() {
		String buttonName = "/";
		
		testOperatorDisplayButtons(buttonName, buttonName);
	}
	
	@Test
	public void testRightBracketOperatorButton() {
		String buttonName = ")";
		
		testOperatorDisplayButtons(buttonName, buttonName);
	}
	
	@Test
	public void testLeftBracketOperatorButton() {
		String buttonName = "(";
		
		testOperatorDisplayButtons(buttonName, buttonName);
	}
	
	@Test
	public void testClearOperatorButton() {
		String buttonName = "CLR";
		
		testOperatorDisplayButtons(buttonName, "");
	}
	
	@Test
	public void testNegateOperatorButton() {
		String buttonName = "+/-";
		
		clickButton("4");
		
		clickButton("+/-");
		assertEquals("-4", field.getText());
	}
	
	@Test
	public void testNegateOperatorButtonToPositive() {
		
		//enter a number
		clickButton("4");
		
		// make it negative
		clickButton("+/-");
		
		// make it positive & check the result
		clickButton("+/-");
		assertEquals("4", field.getText());
	}
	
	@Test
	public void testNegateOperatorButtonAdvanced() {
		
		clickButton("(");
		clickButton("5");
		clickButton("+");
		clickButton("4");
		clickButton(")");
		clickButton("*");
		clickButton("3");
		assertEquals("(5+4)*3", field.getText());
		
		clickButton("+/-");
		assertEquals("(5+4)*-3", field.getText());
	}
	
	@Test
	public void testNegateOperatorButtonToPositiveAdvanced() {
		
		clickButton("(");
		clickButton("5");
		clickButton("+");
		clickButton("4");
		clickButton(")");
		clickButton("*");
		clickButton("3");
		clickButton("+/-");
		assertEquals("(5+4)*-3", field.getText());
		
		clickButton("+/-");
		assertEquals("(5+4)*3", field.getText());
	}
	
	@Test
	public void testCosOperatorButton() {
		clickButton("cos");
		assertEquals("cos(", field.getText());
	}
	
	@Test
	public void testSinOperatorButton() {
		clickButton("sin");
		assertEquals("sin(", field.getText());
	}
	
	@Test
	public void testTanOperatorButton() {
		clickButton("tan");
		assertEquals("tan(", field.getText());
	}
	
	@Test
	public void testPowerOperatorButton() {
		clickButton("power");
		assertEquals("^", field.getText());
	}
	
	@Test
	public void testExpOperatorButton() {
		clickButton("e");
		assertEquals("e^", field.getText());
	}
	
	@Test
	public void testPieOperatorButton() {
		clickButton("pie");
		assertEquals("π", field.getText());
	}
	
	@Test
	public void testDegreeToRadiansToggleOperatorButton() {
		assertEquals("deg", degRadLabel.getText());
		
		clickButton("9");
		clickButton("d/r");
		assertEquals("0.1570796327", field.getText());
		assertEquals("rad", degRadLabel.getText());
		
		clickButton("d/r");
		assertEquals("9.0", field.getText());
		assertEquals("deg", degRadLabel.getText());
	}
	
	@Test
	public void testDegreeToRadiansToggleOperatorButtonOnEmptyString() {
		assertEquals("deg", degRadLabel.getText());
		clickButton("d/r");
		assertEquals("rad", degRadLabel.getText());
	}
	
	/*
	 * Utility Methods:
	 */
	private Component getChildNamed(Component parent, String name) {
		if (parent instanceof Container) {
			Component[] children = ((Container)parent).getComponents();
			for (Component child : children) {
				if (child != null && child.getName() != null)
					if (child.getName().equalsIgnoreCase(name))
						return child;
			}
		}
		return null;
	}
	
	private void testOperatorDisplayButtons(String buttonName, String result) {
		// check the field is there
		assertNotNull(field);
		
		// click the required button
		clickButton(buttonName);
		
		assertEquals(result, field.getText());
	}
	
	private void clickButton(String buttonName) {
		JButton button = (JButton)getChildNamed(uiCalc, buttonName);
		assertNotNull(button);
		
		// click the required button
		button.doClick();
	}

}
