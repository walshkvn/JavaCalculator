package ie.ait.mase.calcproject;

import java.awt.*;

import javax.naming.directory.InvalidAttributesException;
import javax.swing.*;
import javax.swing.border.EtchedBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.jfree.data.xy.XYSeries;
import javax.swing.border.LineBorder;


public class CreateGraphUI extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6592225535558202635L;
	
	private JPanel contentPane;
	private JTextField formulaField;
	private JTextField lowerLimit;
	private JTextField upperLimit;
	private JButton btnGraph;
	private JButton btnMultiply;
	private JTabbedPane tabbedPane;
	private JPanel plFourier;
	
	private JTextArea taMatrixA, taMatrixB;
	
	/*
	 * Fourier
	 */
	private JTextField lValue;
	private JTextField a0Value;
	private JTextField lowerLimitF;
	private JTextField nonZeroTerms;
	private JTextField upperLimitF;
	private JButton btnCalculate;

	/*
	 * Number convertor
	 */
	private JTextField numberIn;
	private final ButtonGroup fromButtonGroup = new ButtonGroup();
	private final ButtonGroup toButtonGroup = new ButtonGroup();
	private JButton btnConvert  = new JButton("Convert"); 
	
	private JRadioButton fromHex;
	private JRadioButton fromBinary;
	private JRadioButton fromDecimal;
	private JRadioButton fromOctal;
	
	private JRadioButton toHex;
	private JRadioButton toBinary;
	private JRadioButton toDecimal;
	private JRadioButton toOctal;
	
	/**
	 * Create the frame.
	 */
	public CreateGraphUI() {
		this("");
	}
	
	public CreateGraphUI(String problem) {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 324);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel plGraph = new JPanel();
		plGraph.setBounds(new Rectangle(0, 0, 400, 300));
		tabbedPane.addTab("Graphing", null, plGraph, null);
		plGraph.setLayout(null);
		
		JLabel lblEnterTheFormula = new JLabel("Enter the formula to graph: e.g. x^2+4x+7");
		lblEnterTheFormula.setBounds(30, 5, 310, 17);
		lblEnterTheFormula.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblEnterTheFormula.setHorizontalAlignment(SwingConstants.CENTER);
		plGraph.add(lblEnterTheFormula);
		
		JLabel label = new JLabel("Note: use 'x' as the variable");
		label.setBounds(104, 25, 173, 16);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		plGraph.add(label);
		
		formulaField = new JTextField();
		formulaField.setBounds(6, 53, 348, 28);
		plGraph.add(formulaField);
		formulaField.setColumns(10); 
		formulaField.setText(problem);
		
		JLabel lblEnterTheLower = new JLabel("Enter the lower range to graph:");
		lblEnterTheLower.setBounds(6, 110, 194, 16);
		plGraph.add(lblEnterTheLower);
		
		JLabel lblEnterTheHigher = new JLabel("Enter the higher range to graph:");
		lblEnterTheHigher.setBounds(6, 153, 200, 16);
		plGraph.add(lblEnterTheHigher);
		
		lowerLimit = new JTextField();
		lowerLimit.setBounds(220, 104, 134, 28);
		plGraph.add(lowerLimit);
		lowerLimit.setColumns(10);
		
		upperLimit = new JTextField();
		upperLimit.setBounds(220, 147, 134, 28);
		plGraph.add(upperLimit);
		upperLimit.setColumns(10);
		
		btnGraph = new JButton("Graph");
		btnGraph.setBounds(273, 187, 81, 29);
		plGraph.add(btnGraph);
		
		/*
		 * Matrices
		 */
		JPanel plMatrix = new JPanel();
		tabbedPane.addTab("Matrices", null, plMatrix, null);
		
		JLabel lblMatrixA = new JLabel("Matrix A:");
		plMatrix.add(lblMatrixA);
		
		JScrollPane scrollPane = new JScrollPane();
		plMatrix.add(scrollPane);
		
		taMatrixA = new JTextArea();
		scrollPane.setViewportView(taMatrixA);
		taMatrixA.setColumns(25);
		taMatrixA.setRows(5);
		
		JLabel lblMatrixB = new JLabel("Matrix B:");
		plMatrix.add(lblMatrixB);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		plMatrix.add(scrollPane_1);
		
		taMatrixB = new JTextArea();
		scrollPane_1.setViewportView(taMatrixB);
		taMatrixB.setRows(5);
		taMatrixB.setColumns(25);
		
		btnMultiply = new JButton("Multiply");
		plMatrix.add(btnMultiply);
		
		btnGraph.addActionListener(this);
		btnMultiply.addActionListener(this);
		
		/*
		 *  *********** Fourier ***********
		 */
		plFourier = new JPanel();
		tabbedPane.addTab("Fourier", null, plFourier, null);
		plFourier.setLayout(null);

//		JTextArea txtrFourierSeriesGrapher = new JTextArea();
//		txtrFourierSeriesGrapher.setBackground(UIManager.getColor("Button.background"));
//		txtrFourierSeriesGrapher.setEditable(false);
//		txtrFourierSeriesGrapher.setText("Fourier Series Grapher");
//		txtrFourierSeriesGrapher.setBounds(116, 11, 191, 22);
//		plFourier.add(txtrFourierSeriesGrapher);
		
		JPanel panel1 = new JPanel();
		panel1.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel1.setBounds(10, 14, 371, 231);
		plFourier.add(panel1);
		panel1.setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 11, 156, 22);
		textArea.setText("Enter value for L: ");
		textArea.setEditable(false);
		textArea.setBackground(SystemColor.menu);
		panel1.add(textArea);
		
		lValue = new JTextField();
		lValue.setBounds(274, 13, 86, 20);
		panel1.add(lValue);
		lValue.setColumns(10);
		
		JTextArea txtrEnterValueFor = new JTextArea();
		txtrEnterValueFor.setText("Enter value for A0: ");
		txtrEnterValueFor.setEditable(false);
		txtrEnterValueFor.setBackground(SystemColor.menu);
		txtrEnterValueFor.setBounds(10, 44, 156, 22);
		panel1.add(txtrEnterValueFor);
		
		a0Value = new JTextField();
		a0Value.setColumns(10);
		a0Value.setBounds(274, 46, 86, 20);
		panel1.add(a0Value);
		
		JTextArea txtrEnterLowerLimit = new JTextArea();
		txtrEnterLowerLimit.setText("Enter lower limit: ");
		txtrEnterLowerLimit.setEditable(false);
		txtrEnterLowerLimit.setBackground(SystemColor.menu);
		txtrEnterLowerLimit.setBounds(10, 77, 156, 22);
		panel1.add(txtrEnterLowerLimit);
		
		lowerLimitF = new JTextField();
		lowerLimitF.setColumns(10);
		lowerLimitF.setBounds(274, 79, 86, 20);
		panel1.add(lowerLimitF);
		
		JTextArea txtrEnterNumberOf = new JTextArea();
		txtrEnterNumberOf.setText("Enter number of non zero terms: ");
		txtrEnterNumberOf.setEditable(false);
		txtrEnterNumberOf.setBackground(SystemColor.menu);
		txtrEnterNumberOf.setBounds(10, 131, 254, 22);
		panel1.add(txtrEnterNumberOf);
		
		nonZeroTerms = new JTextField();
		nonZeroTerms.setColumns(10);
		nonZeroTerms.setBounds(274, 133, 86, 20);
		panel1.add(nonZeroTerms);
		
		JTextArea txtrcalculationsPerformedIn = new JTextArea();
		txtrcalculationsPerformedIn.setForeground(Color.RED);
		txtrcalculationsPerformedIn.setText("(Calculations performed in steps of 0.25)");
		txtrcalculationsPerformedIn.setEditable(false);
		txtrcalculationsPerformedIn.setBackground(SystemColor.menu);
		txtrcalculationsPerformedIn.setBounds(10, 164, 350, 22);
		panel1.add(txtrcalculationsPerformedIn);
		
		btnCalculate = new JButton("Calculate");
		btnCalculate.setBounds(163, 197, 89, 23);
		btnCalculate.addActionListener(this);
		panel1.add(btnCalculate);
		
		JTextArea txtrEnterUpperLimit = new JTextArea();
		txtrEnterUpperLimit.setText("Enter upper limit: ");
		txtrEnterUpperLimit.setEditable(false);
		txtrEnterUpperLimit.setBackground(SystemColor.menu);
		txtrEnterUpperLimit.setBounds(10, 103, 156, 22);
		panel1.add(txtrEnterUpperLimit);
		
		upperLimitF = new JTextField();
		upperLimitF.setColumns(10);
		upperLimitF.setBounds(274, 105, 86, 20);
		panel1.add(upperLimitF);
		
		/*
		 * Number Convertor
		 */
		
		JPanel plNumberConvertor = new JPanel();
		tabbedPane.addTab("Number Convertor", null, plNumberConvertor, null);
		plNumberConvertor.setLayout(null);
		
		numberIn = new JTextField();
		numberIn.setBounds(185, 11, 209, 20);
		plNumberConvertor.add(numberIn);
		numberIn.setColumns(10);
		
		JTextPane txtpnEnterNumberTo = new JTextPane();
		txtpnEnterNumberTo.setBackground(UIManager.getColor("Button.background"));
		txtpnEnterNumberTo.setText("Enter number to convert: ");
		txtpnEnterNumberTo.setBounds(36, 11, 142, 20);
		plNumberConvertor.add(txtpnEnterNumberTo);
		
		JPanel fromPanel = new JPanel();
		fromPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		fromPanel.setBackground(UIManager.getColor("Button.background"));
		fromPanel.setBounds(36, 73, 158, 135);
		plNumberConvertor.add(fromPanel);
		fromPanel.setLayout(null);
		
		fromHex = new JRadioButton("Hex");
		fromButtonGroup.add(fromHex);
		fromHex.setBounds(46, 94, 95, 23);
		fromPanel.add(fromHex);
		
		fromDecimal = new JRadioButton("Decimal");
		fromButtonGroup.add(fromDecimal);
		fromDecimal.setSelected(true);
		fromDecimal.setBounds(46, 68, 95, 23);
		fromPanel.add(fromDecimal);
		
		fromOctal = new JRadioButton("Octal");
		fromButtonGroup.add(fromOctal);
		fromOctal.setBounds(46, 42, 95, 23);
		fromPanel.add(fromOctal);
		
		fromBinary = new JRadioButton("Binary");
		fromButtonGroup.add(fromBinary);
		fromBinary.setBounds(46, 17, 95, 23);
		fromPanel.add(fromBinary);
		
		JPanel numInternalpanel = new JPanel();
		numInternalpanel.setLayout(null);
		numInternalpanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		numInternalpanel.setBackground(SystemColor.menu);
		numInternalpanel.setBounds(236, 73, 158, 135);
		plNumberConvertor.add(numInternalpanel);
		
		toHex = new JRadioButton("Hex");
		toButtonGroup.add(toHex);
		toHex.setBounds(46, 94, 95, 23);
		numInternalpanel.add(toHex);
		
		toDecimal = new JRadioButton("Decimal");
		toButtonGroup.add(toDecimal);
		toDecimal.setSelected(true);
		toDecimal.setBounds(46, 68, 95, 23);
		numInternalpanel.add(toDecimal);
		
		toOctal = new JRadioButton("Octal");
		toButtonGroup.add(toOctal);
		toOctal.setBounds(46, 42, 95, 23);
		numInternalpanel.add(toOctal);
		
		toBinary = new JRadioButton("Binary");
		toButtonGroup.add(toBinary);
		toBinary.setBounds(46, 17, 95, 23);
		numInternalpanel.add(toBinary);
		
		JTextPane txtpnFrom = new JTextPane();
		txtpnFrom.setBackground(UIManager.getColor("Button.background"));
		txtpnFrom.setText("From:");
		txtpnFrom.setBounds(36, 54, 62, 20);
		plNumberConvertor.add(txtpnFrom);
		
		JTextPane txtpnTo = new JTextPane();
		txtpnTo.setText("To:");
		txtpnTo.setBackground(SystemColor.menu);
		txtpnTo.setBounds(236, 54, 62, 20);
		plNumberConvertor.add(txtpnTo);
		
		btnConvert.setBounds(166, 219, 102, 23);
		btnConvert.addActionListener(this);
		plNumberConvertor.add(btnConvert);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnGraph) {
			// check we have a valid expression:
			try {
				if (isValidForGraphing(formulaField.getText(), lowerLimit.getText(), upperLimit.getText())) {

					XYSeries series1 = new XYSeries(formulaField.getText());
					
					// Need to find the value to replace with the upper and lower limits specified
					Calculator calc = new Calculator();
					
					double lLimit = Double.parseDouble(calc.calculate(lowerLimit.getText().replaceAll("-π", "-1*(π)"), true));
					double uLimit = Double.parseDouble(calc.calculate(upperLimit.getText(), true));
					
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
		} else if (e.getSource() == btnMultiply) {
			Matrices matrices = new Matrices();
			try {
				matrices.setMatrixA(taMatrixA.getText());
				matrices.setMatrixB(taMatrixB.getText());
				double[][] result = matrices.multiply();
				taMatrixA.setText("");
				for (int i = 0; i < result.length; i++) {
					for (int j = 0; j < result[0].length; j++) {
						if (result[i][j]%1 == 0) 
							taMatrixA.setText(taMatrixA.getText() + (int)result[i][j]+ " ");
						else
							taMatrixA.setText(taMatrixA.getText() + result[i][j]+ " ");
					}
					taMatrixA.setText(taMatrixA.getText() + "\n");
				}
			} catch (IllegalArgumentException exc) {
				JOptionPane.showInternalMessageDialog(contentPane, exc.getMessage());
			}
		} else if (e.getSource() == btnCalculate) {
			double L = Double.parseDouble(lValue.getText());
	        double a0 =Double.parseDouble(a0Value.getText());
	        double LL = Double.parseDouble(lowerLimitF.getText());
	        double UL = Double.parseDouble(upperLimitF.getText());
	        int nonZero = Integer.parseInt(nonZeroTerms.getText());
	        nonZero = translateToTerms(nonZero);
	        int dist = 20;//(Math.abs(LL)+Math.abs(UL))*2;
	        int arraysize = (int)((Math.abs(LL)+Math.abs(UL))/0.5 )+1;
	        //double[] FSValues = new double[arraysize];
	        double FSV=0;
	        
	        XYSeries series1 = new XYSeries("Fourier Series");

	        for(int i=0; i<arraysize; i++)
	        {
	            FSV = (a0/2)+Calc(nonZero, dist, L, LL);
	            //FSValues[i] = FSV;
	            //System.out.println(LL+" = "+FSValues[i]);
	            series1.add(i, FSV);
	            LL+=0.5;
	            FSV=0;
	        }
	        
	        new GraphUI("Fourier Series", series1);
		} else if (e.getSource() == btnConvert) {
			String numberValue = numberIn.getText();
			
			try {
				if(fromBinary.isSelected()){
				
					if(toDecimal.isSelected()){
						numberIn.setText(Integer.toString(Integer.parseInt(numberValue, 2)));
						fromDecimal.setSelected(true);
					}else if(toHex.isSelected()){
						numberIn.setText(Integer.toHexString(Integer.parseInt(numberValue,2)));
						fromHex.setSelected(true);
					}else if(toOctal.isSelected()){
						numberIn.setText(Integer.toOctalString(Integer.parseInt(numberValue,2)));
						fromOctal.setSelected(true);
					}
				
				}else if(fromDecimal.isSelected()){
				
					if(toBinary.isSelected()){
						numberIn.setText(Integer.toBinaryString(Integer.parseInt(numberValue)));
						fromBinary.setSelected(true);
					}else if(toHex.isSelected()){
						numberIn.setText(Integer.toHexString(Integer.parseInt(numberValue)));
						fromHex.setSelected(true);
					}else if(toOctal.isSelected()){
						numberIn.setText(Integer.toOctalString(Integer.parseInt(numberValue)));
						fromOctal.setSelected(true);
					}
				}else if(fromHex.isSelected()){
				
					if(toBinary.isSelected()){
						numberIn.setText(Integer.toBinaryString(Integer.parseInt(numberValue,16)));
						fromBinary.setSelected(true);
					}else if(toDecimal.isSelected()){
						numberIn.setText(Integer.toString(Integer.parseInt(numberValue,16)));
						fromDecimal.setSelected(true);
					}else if(toOctal.isSelected()){
						numberIn.setText(Integer.toOctalString(Integer.parseInt(numberValue,16)));
						fromOctal.setSelected(true);
					}
				}else if(fromOctal.isSelected()){
				
					if(toBinary.isSelected()){
						numberIn.setText(Integer.toBinaryString(Integer.parseInt(numberValue,8)));
						fromBinary.setSelected(true);
					}else if(toDecimal.isSelected()){
						numberIn.setText(Integer.toString(Integer.parseInt(numberValue,8)));
						fromDecimal.setSelected(true);
					}else if(toHex.isSelected()){
						numberIn.setText(Integer.toHexString(Integer.parseInt(numberValue,8)));	
						fromHex.setSelected(true);
					}
				}
			} catch (NumberFormatException e1) {
				JOptionPane.showInternalMessageDialog(contentPane, "Invalid Input");
				numberIn.setText("");
				e1.printStackTrace();
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
		else if (!lowerLimit.matches("-?.?π?\\d*")) // a minus and one or more digits
			throw new InvalidAttributesException ("310: Lower limit is not a number");
		else if (!lowerLimit.matches("-?.?π?\\d*")) // a minus and one or more digits
			throw new InvalidAttributesException ("410: Upper limit is not a number");
		
		// check the formula contains an entity to apply to the limits
		// TODO check that the problem entered is valid.
		
		return valid;
	}
	
	/*
	 * Fourier Methods
	 */
	
	public static double Calc(int k, int dist, double L, double t) {
        double result=0;
        if(k<=0)
            return result; 
        result += (dist/(k*Math.PI))*Math.sin((k*Math.PI/L)*t)+Calc(k-2,dist,L, t);
        return result;
    }
    public static int translateToTerms(int t) {
        int count=1;
        for(int i=1; i<t; i++)
        {
            count+=2;
        }
        System.out.println("Terms = "+count);
        return count;
    }
}
