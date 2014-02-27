package ie.ait.mase.calcproject;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Matrices {

	private int rowsA, rowsB, columnsA, columnsB;
	private boolean validMatrices = false;
	private double[][] matrixA, matrixB;
	
	public void setMatrixA(String matrixStr) throws IllegalArgumentException {
		if (validateMatrixString(matrixStr)) {
			matrixA = generateMatrix(matrixStr) ;
			rowsA = matrixA.length;
			columnsA = matrixA[0].length;
		} else
			throw new IllegalArgumentException("Invlaid matrix size or characters entered!");
		
		// check that the matrices may be multiplied by each other
		if (columnsB != 0) {
			if (columnsA != rowsB && columnsB != rowsA)
				throw new IllegalArgumentException("Matrices are not compatible!");
			else
				validMatrices = true;
		}
	}

	public void setMatrixB(String matrixStr) throws IllegalArgumentException {
		if (validateMatrixString(matrixStr)) {
			matrixB = generateMatrix(matrixStr) ;
			rowsB = matrixB.length;
			columnsB = matrixB[0].length;
		} else
			throw new IllegalArgumentException("Invlaid matrix size or characters entered!");
		
		// check that the matrices may be multiplied by each other
		if (columnsA != 0) {
			if (columnsA != rowsB && columnsB != rowsA)
				throw new IllegalArgumentException("Matrices are not compatible!");
			else 
				validMatrices = true;
		}
		
	}
	
	public double[][] multiply() {
		double[][] matrixResult = new double[][]{}; 
		if (validMatrices) { // make sure these are valid matrices
			if (columnsA == rowsB) {
				matrixResult = new double[matrixA.length][matrixB[0].length];
				for (int i = 0; i < matrixA.length; i++) {
					for (int j = 0; j < matrixB[0].length; j++) {
						double r = 0;
						for (int k = 0; k < matrixB.length; k++) {
							r = r + matrixA[i][k]*matrixB[k][j];
						}
						matrixResult[i][j] = r;
					}
				}
				
			} else if (columnsB == rowsA) {
				matrixResult = new double[matrixB.length][matrixA[0].length];
				for (int i = 0; i < matrixB.length; i++) {
					for (int j = 0; j < matrixA[0].length; j++) {
						double r = 0;
						for (int k = 0; k < matrixA.length; k++) {
							r = r + matrixB[i][k]*matrixA[k][j];
						}
						matrixResult[i][j] = r;
					}
				}
			}
		}	
		return matrixResult;
	}
	
	private boolean validateMatrixString(String matrixStrA) {
		
		Scanner sc = new Scanner(matrixStrA);
		int numOfRows = 0, numOfCols = 0;
		boolean valid = true;
		
		// check that the string doesn't contain anything but numbers or a dot
		while (sc.hasNextLine()) {
			String tempStr = sc.nextLine();
			for (int i = 0; i < tempStr.length(); i++) {
				char nextToken = tempStr.charAt(i);
				if ((nextToken >= '0' && nextToken <= '9') || nextToken == '.') 
					valid = true;
				else if (nextToken == ' ') {}
				else {
					valid = false;
					break;
				}
			}
		}
		
		// determine the size of the matrix
		sc.close();
		sc = new Scanner(matrixStrA);
		while (sc.hasNextLine() && valid==true) {
			numOfRows++;
			String tempStr = sc.nextLine();
			Scanner scLine = new Scanner(tempStr);
			
			// Check that there are an even number of columns
			int col = 0;
			while (scLine.hasNextDouble()) {
				// count the number of columns
				if (numOfRows == 1)
					numOfCols++;
				else 
					col++;
				scLine.nextDouble();
			} 
			if (numOfRows >1 && col != numOfCols)
				valid = false;
			scLine.close();
		}
		sc.close();
		return valid;
	}
	
	private double[][] generateMatrix(String matrixStr) {
		Scanner sc = new Scanner(matrixStr);
		int numOfRows = 0, numOfCols = 0;
	
		// determine required size:
		while (sc.hasNextLine()) {
			numOfRows++;
			String tempStr = sc.nextLine();
			
			if (numOfRows == 1) {
				Scanner scLine = new Scanner(tempStr);
				while (scLine.hasNextDouble()) {
					// count the number of columns
						numOfCols++;
						scLine.nextDouble();
				}
				scLine.close();
			}
		}
		
		// create the matrix based on the size
		double[][] matrixTemp = new double[numOfRows][numOfCols];
		sc.close();
		sc = new Scanner(matrixStr);
		numOfRows = 0;
		while (sc.hasNextLine()) {
			String tempStr = sc.nextLine();
			Scanner scLine = new Scanner(tempStr);
			numOfCols = 0;
			while (scLine.hasNextDouble()) {
				matrixTemp[numOfRows][numOfCols] = scLine.nextDouble();
				numOfCols++;
			}
			numOfRows++;
			scLine.close();
		}
		sc.close();
		
		return matrixTemp;
	}
}
