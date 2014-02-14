package ie.ait.mase.calcproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.peer.ButtonPeer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class CalculatorUI extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 400, HEIGHT = 450, NUM_OF_COLUMNS = 5, 
			NUM_OF_ROWS = 8, BUTTON_PADDING = 2;
	private final double PIE = 3.141592654;
	private final char ZERO = 47, NINE = 57, MINUS = '-', DOT = '.';

	private GridBagLayout calcLayout;
	private GridBagConstraints calcGbc; // allows us to position the buttons

	// Array for our number Buttons
	private JButton[] numberButtons;
	private JButton[] oppButtons;

	private JTextField calcField;

	private JLabel degRad;

	// button positioning
	// [0] = gridx, [1] = gridy, [2] = gridwidth, [3] = gridheight[]
	private int[][] numConstraints = new int [][] {
			{0, 7, 2, 1},
			{0, 6, 1, 1},
			{1, 6, 1, 1},
			{2, 6, 1, 1},
			{0, 5, 1, 1},
			{1, 5, 1, 1},
			{2, 5, 1, 1},
			{0, 4, 1, 1},
			{1, 4, 1, 1},
			{2, 4, 1, 1}
	};

	private int[][] oppConstraints = new int [][] {
			{2, 7, 1, 1}, //.
			{4, 6, 1, 2}, // =
			{3, 5, 1, 1}, // + 
			{4, 5, 1, 1}, // - 
			{3, 4, 1, 1}, // *
			{4, 4, 1, 1}, // /
			{1, 2, 1, 1}, // (
			{0, 2, 1, 1}, // )
			{3, 7, 1, 1}, // +/-
			{3, 6, 1, 1}, // CLR
			{2, 3, 1, 1}, // sin
			{3, 3, 1, 1}, // cos
			{4, 3, 1, 1}, // tan
			{0, 3, 1, 1}, // sqr root
			{1, 3, 1, 1}, // power
			{2, 2, 1, 1}, // ln
			{3, 2, 1, 1}, // log
			{4, 2, 1, 1}, // blog
			{0, 1, 1, 1}, //e
			{1, 1, 1, 1}, //pie
			{2, 1, 1, 1}, //Deg/Radians
			{3, 1, 2, 1}  //Graph
	};

	// Constructor
	public CalculatorUI () {
		// set the size of the JPanel
		setPreferredSize(new Dimension(WIDTH, HEIGHT));

		// set up our grid for our buttons
		calcLayout = new GridBagLayout();
		int width =  (WIDTH/NUM_OF_COLUMNS) - (BUTTON_PADDING*2);
		calcLayout.columnWidths = new int[] {width, width, width, width, width};
		calcLayout.rowHeights = new int[] {HEIGHT/NUM_OF_ROWS, HEIGHT/NUM_OF_ROWS, HEIGHT/NUM_OF_ROWS, 
				HEIGHT/NUM_OF_ROWS, HEIGHT/NUM_OF_ROWS, HEIGHT/NUM_OF_ROWS, HEIGHT/NUM_OF_ROWS};
		setLayout(calcLayout);

		// place the buttons
		calcGbc = new GridBagConstraints();

		// create our number buttons
		numberButtons = new JButton[10];
		for (int i = 0; i < numberButtons.length; i++) {
			numberButtons[i] = new JButton("" + i);
			numberButtons[i].addActionListener(this);
			numberButtons[i].setName("" + i);

			// position our buttons
			calcGbc.gridx = numConstraints[i][0];
			calcGbc.gridy = numConstraints[i][1];
			calcGbc.gridwidth = numConstraints[i][2];
			calcGbc.gridheight = numConstraints[i][3];
			calcGbc.fill = GridBagConstraints.BOTH; // fill the whole grid area
			calcGbc.insets = new Insets(BUTTON_PADDING, BUTTON_PADDING, BUTTON_PADDING, BUTTON_PADDING); // add some padding around buttons

			// add the buttons
			add(numberButtons[i], calcGbc);
		}

		// create the operation buttons & assign names (required for JUnit Testing):
		oppButtons = new JButton[22];
		oppButtons[0] = new JButton("."); oppButtons[0].setName(".");
		oppButtons[1] = new JButton("="); oppButtons[1].setName("=");
		oppButtons[2] = new JButton("+"); oppButtons[2].setName("+");
		oppButtons[3] = new JButton("-"); oppButtons[3].setName("-");
		oppButtons[4] = new JButton("*"); oppButtons[4].setName("*");
		oppButtons[5] = new JButton("/"); oppButtons[5].setName("/");
		oppButtons[6] = new JButton(")"); oppButtons[6].setName(")");
		oppButtons[7] = new JButton("("); oppButtons[7].setName("(");
		oppButtons[8] = new JButton("+/-"); oppButtons[8].setName("+/-");
		oppButtons[9] = new JButton("CLR"); oppButtons[9].setName("CLR");
		oppButtons[10] = new JButton("sin"); oppButtons[10].setName("sin");
		oppButtons[11] = new JButton("cos"); oppButtons[11].setName("cos");
		oppButtons[12] = new JButton("tan"); oppButtons[12].setName("tan");
		oppButtons[13] = new JButton("\u207F\u221A"); oppButtons[13].setName("root"); //square root 
		oppButtons[14] = new JButton("x\u207F"); oppButtons[14].setName("power");
		oppButtons[15] = new JButton("ln"); oppButtons[15].setName("ln");
		oppButtons[16] = new JButton("log"); oppButtons[16].setName("log");
		oppButtons[17] = new JButton("blog"); oppButtons[17].setName("blog");
		oppButtons[18] = new JButton("e"); oppButtons[18].setName("e");
		oppButtons[19] = new JButton("π"); oppButtons[19].setName("pie");
		oppButtons[20] = new JButton("D/R"); oppButtons[20].setName("d/r");
		oppButtons[21] = new JButton("Graphs"); oppButtons[21].setName("graph");

		// position the buttons
		for (int i = 0; i < oppButtons.length; i++) {
			calcGbc.gridx = oppConstraints[i][0];
			calcGbc.gridy = oppConstraints[i][1];
			calcGbc.gridwidth = oppConstraints[i][2];
			calcGbc.gridheight = oppConstraints[i][3];
			calcGbc.fill = GridBagConstraints.BOTH; // fill the whole grid area
			calcGbc.insets = new Insets(BUTTON_PADDING, BUTTON_PADDING, BUTTON_PADDING, BUTTON_PADDING); // add some padding around buttons

			oppButtons[i].addActionListener(this);

			// add the buttons
			add(oppButtons[i], calcGbc);
		}

		calcField = new JTextField();
		calcField.setName("display");
		calcField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		//calcField.setEditable(false);
		calcField.setFont(new Font("Arial", Font.PLAIN, 24));
		// modify the constants for the field
		calcGbc.gridx=0;
		calcGbc.gridy=0;
		calcGbc.gridwidth=4;
		calcGbc.gridheight=1;

		add(calcField, calcGbc);

		degRad = new JLabel("deg", SwingConstants.CENTER);
		degRad.setName("d/r toggle");
		degRad.setFont(new Font("Arial", Font.PLAIN, 18));
		// modify the constants for the field
		calcGbc.gridx=4;
		calcGbc.gridy=0;
		calcGbc.gridwidth=1;
		calcGbc.gridheight=1;
		add(degRad, calcGbc);
	}

	public void actionPerformed(ActionEvent e) {
		Calculator calc = new Calculator();

		for (int i = 0; i < numberButtons.length; i++) {
			if (e.getSource() == numberButtons[i]) {
				calcField.setText(calcField.getText() + i);
			} 
		} 

		if(e.getSource() == oppButtons[0] && !calcField.getText().contains(".")) // decimal point button
			calcField.setText(calcField.getText() + ".");
		else if (e.getSource() == oppButtons[2]) { 			// + Button
			calcField.setText(calcField.getText() + "+");
		} else if (e.getSource() == oppButtons[3]) {		// - Button
			calcField.setText(calcField.getText() + "-");
		} else if (e.getSource() == oppButtons[4]) {		// * Button
			calcField.setText(calcField.getText() + "*");
		} else if (e.getSource() == oppButtons[5]) {		// / Button
			calcField.setText(calcField.getText() + "/");
		} else if (e.getSource() == oppButtons[6]) {		// ) Button
			calcField.setText(calcField.getText() + ")");
		} else if (e.getSource() == oppButtons[7]) {		// ( Button
			calcField.setText(calcField.getText() + "(");
		} else if (e.getSource() == oppButtons[9]) { 		// CLR Button
			calcField.setText("");
		} else if (e.getSource() == oppButtons[10]) {		// sin Button
			calcField.setText(calcField.getText() + "sin(");
		} else if (e.getSource() == oppButtons[11]) {		// cos Button
			calcField.setText(calcField.getText() + "cos(");
		} else if (e.getSource() == oppButtons[12]) {		// tan Button
			calcField.setText(calcField.getText() + "tan(");
		} else if (e.getSource() == oppButtons[13]) {		// root Button	

			String rootMultipler = (String)JOptionPane.showInputDialog("Enter the root multipler (2=squared, 3=cubed, etc):", 2 );
			calcField.setText(calcField.getText() + "(" + rootMultipler + "\u221A");

		} else if (e.getSource() == oppButtons[14]) {		// power Button
			calcField.setText(calcField.getText() + "^");
		} else if (e.getSource() == oppButtons[15]) {		// ln Button
			calcField.setText(calcField.getText() + "ln(");
		} else if (e.getSource() == oppButtons[16]) {		// log Button
			calcField.setText(calcField.getText() + "log(");
		} else if (e.getSource() == oppButtons[17]) {		// blog Button
			calcField.setText(calcField.getText() + "blog");
		} else if (e.getSource() == oppButtons[18]) {		// e Button
			calcField.setText(calcField.getText() + "e");
		} else if (e.getSource() == oppButtons[19]) {		// π Button
			calcField.setText(calcField.getText() + "π");
		} else if (e.getSource() == oppButtons[20]) {		// degree->radians toggle Button
			if (canConvert(calcField.getText())) {
				double currentValue = Double.parseDouble(calcField.getText());
				if (degRad.getText().equalsIgnoreCase("deg")) {
					calcField.setText("" + currentValue * (PIE/180));
					degRad.setText("rad");
				} else {
					calcField.setText("" + currentValue * (180/PIE));
					degRad.setText("deg");
				}
			} else {
				// toggle the Degree/Radian Display
				if (degRad.getText().equalsIgnoreCase("deg")) {
					degRad.setText("rad");
				} else {
					degRad.setText("deg");
				}
			}
		} else if (e.getSource() == oppButtons[21]) { 			// graph Button
			try {
				CreateGraphUI createGraph = new CreateGraphUI(calcField.getText());
				createGraph.setVisible(true);
			} catch (Exception excpt) {
				excpt.printStackTrace();
			}
		} else if (e.getSource() == oppButtons[8]) { 			// +/- Button
			// check if the last item entered is a number
			StringBuffer lastNumberEntered = new StringBuffer();
			char nextChar = 0;
			for (int i = calcField.getText().length()-1; i >= 0; i--) {
				nextChar = calcField.getText().charAt(i);
				if ((nextChar >= ZERO && nextChar <= NINE) || nextChar == DOT) {
					lastNumberEntered.insert(0, nextChar);
				} else if (nextChar == MINUS) {
					// need to check if the number is negative or if part of an expression
					if (i != 0) {
						char precedingChar = calcField.getText().charAt(i-1);
						if (precedingChar >= ZERO && precedingChar <= NINE)
							nextChar = 0; // number is part of an expression so the lastNumberEntered is positive
					}
					break; // break out of the for loop as we now have all the information we need
				} else
					break; // break out of the for loop as we now have all the information we need
			}

			// if nextChar is still MINUS then the number is negative and we need to make it positive
			if (nextChar == MINUS)
				calcField.setText(replaceLastInstance(calcField.getText(), "-" + lastNumberEntered, lastNumberEntered.toString()));
			else
				// we need to make the number negative
				calcField.setText(replaceLastInstance(calcField.getText(), lastNumberEntered.toString(), "-" + lastNumberEntered));

		} else if (e.getSource() == oppButtons[1]) { 				// = Button ************
			// check if the problem entered for inconsistencies
			if (isValidProblem(calcField.getText())) {
				// calculate a result for the given problem
				if (degRad.getText().equalsIgnoreCase("deg"))
					calcField.setText(calc.calculate(calcField.getText(), false));
				else
					calcField.setText(calc.calculate(calcField.getText(), true));
			} else {
				// display an error message
				JOptionPane.showMessageDialog(null, "You have entered an invalid problem, please review for closing parentheses, double operators, etc.", 
						"Invalid Problem Specified", JOptionPane.INFORMATION_MESSAGE);
			} 

		}
	}

	private boolean canConvert(String problem) {
		boolean possible = true;
		if (!problem.equalsIgnoreCase("")) {
			for (int i = 0; i < problem.length(); i++) {
				if ((problem.charAt(i) <= ZERO && problem.charAt(i) >= NINE) 
						&& problem.charAt(i) != DOT) {
					possible = false;
					break;
				}
			}
		} else 
			possible = false;

		return possible;
	}

	private boolean isValidProblem(String problem) {
		// Check for closing brackets
		int bracket = 0;

		for (int i = 0; i < problem.length(); i++) {
			if (problem.charAt(i) == '(')
				bracket++;
			else if (problem.charAt(i) == ')')
				bracket--;
		}

		if (bracket == 0)
			return true;
		else
			return false;
	}


	private String replaceLastInstance(String sourceStr, String findStr, String replaceStr) {

		int lastIndex = sourceStr.lastIndexOf(findStr);
		if (lastIndex > 0)
			return sourceStr.substring(0, lastIndex) + replaceStr;
		else
			return sourceStr.substring(0, lastIndex) + replaceStr;
	}

	public static void main(String[] args) {
		JFrame calcFrame = new JFrame("Calculator");
		calcFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // close the window the program terminates
		calcFrame.setResizable(false);
		calcFrame.setLayout(new BorderLayout()); // allows to center on screen
		calcFrame.add(new CalculatorUI(), BorderLayout.CENTER);
		calcFrame.pack(); // to set the size of the frame to the size of the panel
		calcFrame.setLocationRelativeTo(null); // center the frame on the screen
		calcFrame.setVisible(true);

	}

}