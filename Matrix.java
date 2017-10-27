import java.util.Scanner;
import java.text.DecimalFormat;

public class Matrix {

	private static class Help {
		private static String leftFill(String givenString, int width, String fillChar) {
			if (givenString.length()>=width) {
				return givenString;
			} else {
				String returnString = givenString;
				int difference = width - givenString.length();
				for (int i=0; i<difference; i++) {
					returnString=fillChar+returnString;
				}
				return returnString;
			}
		}
		private static int entryMaxLength(Matrix M, DecimalFormat givenFormat) {
			int maxLength = 0;
			int currentLength = 0;
			for (int i=0; i<M.getm(); i++) { //ith row
				for (int j=0; j<M.getn(); j++) { //jth column
					currentLength = givenFormat.format(M.getAij(i,j)).length();
					if (currentLength>maxLength) {
						maxLength = currentLength;
					}
				}
			}
			return maxLength;
		}
	}

	//instance variables
	private int m;
	private int n;
	private double[][] matrix;

	//main function for testing
	public static void main(String[] args) {
		System.out.print("Input matrix A. ");
		Matrix A = new Matrix();
		System.out.println("Matrix A is: \n"+A.toString()+"\n");
		
		System.out.print("Input matrix B. ");
		Matrix B = new Matrix();
		System.out.println("Matrix B is: \n"+B.toString()+"\n\n"+"AB = ");
		try {
			System.out.println(A.multiplies(B));
		} catch(MatrixIncompatibleException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
	}
	
	//constructors
	public Matrix() {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("To create an m * n matrix, please enter m and n:");
		m=keyboard.nextInt();
		n=keyboard.nextInt();
		matrix = new double[m][n];
		System.out.println("now, enter the rows of the matrix:");

		for (int i=0; i<m; i++) { //ith row
			for (int j=0; j<n; j++) { //jth column
				matrix[i][j]=keyboard.nextDouble();
			}
		}
	}
	public Matrix(int m, int n, double[][] matrix) {
		this.m=m;
		this.n=n;
		this.matrix=matrix;
	}

	//get and set methods
	public double getAij(int i, int j) {
		return matrix[i][j];
	} 
	public void setAij(double value, int i, int j) {
		matrix[i][j]=value;
	}
	public int getm() {
		return m;
	}
	public int getn() {
		return n;
	}


	//major methods
	public boolean equals(Matrix B) {
		if (this.m!=B.getm() || this.n!=B.getn()) {
			return false;
		} else {
			for (int i=0; i<this.m; i++) {
				for (int j=0; j<this.n; j++) {
					if (this.matrix[i][j]!=B.getAij(i,j)) {
						return false;
					}
				}
			}
			return true;
		}
	}

	public void adds(Matrix B) throws MatrixIncompatibleException {
		if (m!=B.getm() || n!=B.getn()) {
			throw new MatrixIncompatibleException();
		}

		for (int i=0; i<m; i++) {
			for (int j=0; j<n; j++) {
				matrix[i][j]+=B.getAij(i,j);
			}
		}
	}

	public Matrix multiplies(Matrix B) throws MatrixIncompatibleException { // return new matrix, not modifying old matrix
		int newm=this.m;
		int oldn=this.n;
		int newn=B.getn();
		double sum=0;
		if (oldn!=B.getm()) {
			throw new MatrixIncompatibleException(this,B);
		}
		double[][] returnMatrix = new double[newm][newn];
		for (int i=0; i<newm; i++) {
			for (int j=0; j<newn; j++) {
				for (int k=0; k<oldn; k++) {
					sum+=this.matrix[i][k]*B.getAij(k,j);
				}
				returnMatrix[i][j]=sum;
				sum=0;
			}
		}
		return new Matrix(newm,newn,returnMatrix);
	}
	public Matrix transpose() { // return new matrix, not modifying old matrix
		double[][] returnMatrix = new double[n][m];
		for (int i=0; i<n; i++) {
			for (int j=0; j<m; j++) {
				returnMatrix[i][j]=this.matrix[j][i];
			} 
		}
		return new Matrix(n,m,returnMatrix);
	}

	//elementary row operations
	public void swap(int row1, int row2) {
		double[] rowReserve=null;
		rowReserve=matrix[row1];
		matrix[row1]=matrix[row2];
		matrix[row2]=rowReserve;
	}
	public void scale(int row, double constant) { //row starts from 0, not 1
		for (int j=0; j<n; j++) {
			matrix[row][j]*=constant;
		}
	}
	public void addToRow(int row1, int row2, double multipleOfRow2) {
		for (int j=0; j<n; j++) {
			matrix[row1][j]+=multipleOfRow2*matrix[row2][j];
		}
	}

	//toString method
	public String toString() {
		DecimalFormat standardDouble = new DecimalFormat("0.##");
		String s="";
		int maxLength = Help.entryMaxLength(this,standardDouble);
		int columnMaxLength = 0; //to make it look better
		int currentColumnLength = 0;
		for (int i=0; i<m; i++) {
			currentColumnLength = standardDouble.format(matrix[i][0]).length();
			if (currentColumnLength > columnMaxLength) {
				columnMaxLength = currentColumnLength;
			}
		}
		s+=" _";
		for (int i=0; i<((maxLength+1)*(n-1)+columnMaxLength+2); i++) {
			s+=" ";
		}
		s+="_\n";

		for (int i=0; i<m; i++) { //(i+1)th row
			if (i!=(m-1)) {
				s+="|  ";
			} else {
				s+="|_ ";
			}
			for (int j=0; j<n; j++) { //(j+1)th column
				if (j!=0) {
					s+=Help.leftFill(standardDouble.format(matrix[i][j]),maxLength+1," ");
				} else {
					s+=Help.leftFill(standardDouble.format(matrix[i][j]),columnMaxLength," ");
				}
			}
			if (i!=(m-1)) {
				s+="  |\n";
			} else {
				s+=" _|";
			}
		}
		return s;
	}
}