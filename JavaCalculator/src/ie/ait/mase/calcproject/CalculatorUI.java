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
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CalculatorUI extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 320, HEIGHT = 480, NUM_OF_COLUMNS = 4, 
			NUM_OF_ROWS = 6, BUTTON_PADDING = 2;
	
	private GridBagLayout calcLayout;
	private GridBagConstraints calcGbc; // allows us to position the buttons
	
	// Array for our number Buttons
	private JButton[] numberButtons;
	private JButton[] oppButtons;
	
	private JTextField calcField;
	
	// button positioning
	// [0] = gridx, [1] = gridy, [2] = gridwidth, [3] = gridheight[]
	private int[][] numConstraints = new int [][] {
			{0, 5, 2, 1},
			{0, 4, 1, 1},
			{1, 4, 1, 1},
			{2, 4, 1, 1},
			{0, 3, 1, 1},
			{1, 3, 1, 1},
			{2, 3, 1, 1},
			{0, 2, 1, 1},
			{1, 2, 1, 1},
			{2, 2, 1, 1},
	};
	
	private int[][] oppConstraints = new int [][] {
			{2, 5, 1, 1},
			{3, 4, 1, 2},
			{3, 3, 1, 1},
			{3, 2, 1, 1},
			{3, 1, 1, 1},
			{2, 1, 1, 1},
			{1, 1, 1, 1},
			{0, 1, 1, 1},
	};
	
	// Constructor
	public CalculatorUI () {
		// set the size of the JPanel
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		// set up our grid for our buttons
		calcLayout = new GridBagLayout();
		calcLayout.columnWidths = new int[] {WIDTH/NUM_OF_COLUMNS, WIDTH/NUM_OF_COLUMNS, 
				WIDTH/NUM_OF_COLUMNS, WIDTH/NUM_OF_COLUMNS};
		calcLayout.rowHeights = new int[] {HEIGHT/NUM_OF_ROWS, HEIGHT/NUM_OF_ROWS, HEIGHT/NUM_OF_ROWS, 
				HEIGHT/NUM_OF_ROWS, HEIGHT/NUM_OF_ROWS, HEIGHT/NUM_OF_ROWS};
		setLayout(calcLayout);
		
		// place the buttons
		calcGbc = new GridBagConstraints();
		
		// create our number buttons
		numberButtons = new JButton[10];
		for (int i = 0; i < numberButtons.length; i++) {
			numberButtons[i] = new JButton("" + i);
			numberButtons[i].addActionListener(this);
			
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
		
		// create the operation buttons:
		oppButtons = new JButton[8];
		oppButtons[0] = new JButton(".");
		oppButtons[1] = new JButton("=");
		oppButtons[2] = new JButton("+");
		oppButtons[3] = new JButton("-");
		oppButtons[4] = new JButton("*");
		oppButtons[5] = new JButton("/");
		oppButtons[6] = new JButton("+/-");
		oppButtons[7] = new JButton("CLR");
		
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
		calcField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		//calcField.setEditable(false);
		calcField.setFont(new Font("Arial", Font.PLAIN, 24));
		calcGbc.gridx=0;
		calcGbc.gridy=0;
		calcGbc.gridwidth=4;
		calcGbc.gridheight=1;
		
		add(calcField, calcGbc);
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
		else if (e.getSource() == oppButtons[2]) // + Button
			calcField.setText(calcField.getText() + "+");
		else if (e.getSource() == oppButtons[3]) // + Button
			calcField.setText(calcField.getText() + "-");
		else if (e.getSource() == oppButtons[4]) // * Button
			calcField.setText(calcField.getText() + "*");
		else if (e.getSource() == oppButtons[5]) // + Button
			calcField.setText(calcField.getText() + "/");
		else if (e.getSource() == oppButtons[6]) { // +/- Button
			// TODO Need get the last number entered.
			
			calcField.setText("" + Integer.parseInt(calcField.getText())*-1);
		} else if (e.getSource() == oppButtons[7]) // CLR Button
			calcField.setText("");
		else if (e.getSource() == oppButtons[1]) { // = Button
			calcField.setText(calc.calculate(calcField.getText()));
		}
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
