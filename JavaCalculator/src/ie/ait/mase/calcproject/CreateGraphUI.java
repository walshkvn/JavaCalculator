package ie.ait.mase.calcproject;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.naming.directory.InvalidAttributesException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import java.awt.Font;

public class CreateGraphUI extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField formulaField;
	private JTextField lowerLimit;
	private JTextField upperLimit;
	private JButton btnGraph;

	/**
	 * Create the frame.
	 */
	public CreateGraphUI() {
		this("");
	}
	
	public CreateGraphUI(String problem) {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 400, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblEnterTheFormula = new JLabel("Enter the formula to graph:e.g. x^2+4x+7");
		lblEnterTheFormula.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblEnterTheFormula.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterTheFormula.setBounds(5, 11, 379, 27);
		panel.add(lblEnterTheFormula);
		
		JLabel label = new JLabel("Note: use 'x' as the variable");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(5, 38, 379, 27);
		panel.add(label);
		
		formulaField = new JTextField();
		formulaField.setBounds(15, 83, 335, 39);
		panel.add(formulaField);
		formulaField.setColumns(10);
		formulaField.setText(problem);
		
		JLabel lblEnterTheLower = new JLabel("Enter the lower range to graph:");
		lblEnterTheLower.setBounds(25, 159, 207, 16);
		panel.add(lblEnterTheLower);
		
		JLabel lblEnterTheHigher = new JLabel("Enter the higher range to graph:");
		lblEnterTheHigher.setBounds(24, 205, 208, 16);
		panel.add(lblEnterTheHigher);
		
		lowerLimit = new JTextField();
		lowerLimit.setBounds(269, 153, 81, 28);
		panel.add(lowerLimit);
		lowerLimit.setColumns(10);
		
		upperLimit = new JTextField();
		upperLimit.setBounds(269, 193, 81, 28);
		panel.add(upperLimit);
		upperLimit.setColumns(10);
		
		btnGraph = new JButton("Graph");
		btnGraph.setBounds(233, 233, 117, 29);
		panel.add(btnGraph);
		
		btnGraph.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnGraph) {
			// check we have a valid expression:
			try {
				if (isValidForGraphing(formulaField.getText(), lowerLimit.getText(), upperLimit.getText())) {

					XYSeries series1 = new XYSeries(formulaField.getText());
					double lLimit = Double.parseDouble(lowerLimit.getText());
					double uLimit = Double.parseDouble(upperLimit.getText());
					
					// Need to find the value to replace with the upper and lower limits specified
					Calculator calc = new Calculator();
					
					for(double i = lLimit; i <= uLimit; i=i+0.1) {
						String problem = formulaField.getText().toLowerCase().replaceAll("x", ""+i);
						series1.add(i, Double.parseDouble(calc.calculate(problem, true)));
					}
					
					new GraphUI(formulaField.getText(), series1);
					
				}
			} catch (InvalidAttributesException iaExcpt) {
				String error = iaExcpt.getExplanation().substring(0, 3);
				if (error.equalsIgnoreCase("300"))
					JOptionPane.showInternalMessageDialog(contentPane, "Please enter a lower limit!");
				else if (error.equalsIgnoreCase("310"))
					JOptionPane.showInternalMessageDialog(contentPane, "Lower Limit is not a number!");
				else if (error.equalsIgnoreCase("400"))
					JOptionPane.showInternalMessageDialog(contentPane, "Please enter an upper limit!");
				else if (error.equalsIgnoreCase("410"))
					JOptionPane.showInternalMessageDialog(contentPane, "Upper Limit is not a number!");
				else if (error.equalsIgnoreCase("200"))
					JOptionPane.showInternalMessageDialog(contentPane, "Please enter a a problem to solve!");
			}
		}
		
	}


	private boolean isValidForGraphing(String formula, String lowerLimit, String upperLimit) throws InvalidAttributesException {
		boolean valid = true;
		
		//check the limits
		if (lowerLimit.equalsIgnoreCase(""))
			throw new InvalidAttributesException ("300: Lower Limit is blank");
		else if (upperLimit.equalsIgnoreCase(""))
			throw new InvalidAttributesException ("400: Upper Limit is blank");
		else if (formula.equalsIgnoreCase(""))
			throw new InvalidAttributesException ("200: Problem to solve is blank");
		else if (!lowerLimit.matches("-?\\d+")) // a minus and one or more digits
			throw new InvalidAttributesException ("310: Lower limit is not a number");
		else if (!lowerLimit.matches("-?\\d+")) // a minus and one or more digits
			throw new InvalidAttributesException ("410: Upper limit is not a number");
		
		// check the formula contains an entity to apply to the limits
		// TODO check that the problem entered is valid.
		
		return valid;
	}
}
