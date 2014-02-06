package ie.ait.mase.calcproject;

import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

class CalculatorChart extends JPanel {
	
	public static final int WIDTH = 700, HEIGHT = 480;
	
	CalculatorChart (){
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		DefaultPieDataset pieDataset = new DefaultPieDataset();
		pieDataset.setValue("JavaWorld", new Integer(75));
		pieDataset.setValue("Other", new Integer(25));
		JFreeChart chart = ChartFactory.createPieChart
		                     ("Sample Pie Chart",   // Title
		                      pieDataset
		                     );
		
		BufferedImage image = chart.createBufferedImage(500,300);
		JLabel lblChart = new JLabel();
		lblChart.setIcon(new ImageIcon(image));
		add(lblChart);
	}
	
}
