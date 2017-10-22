import java.util.Scanner;
import java.util.ArrayList;


public class Matrix { //issue: integers are used in the matrix instead of Doubles and Integers

	private static class Help {
		public static String leftFill(String givenString, int width, String fillChar) {
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

		public static int entryMaxLength(Matrix M) {
			int maxLength = 0;
			int currentLength = 0;
			for (int i=0; i<M.getm(); i++) { //ith row
				for (int j=0; j<M.getn(); j++) { //jth column
					currentLength = Integer.toString(M.getAij(i,j)).length();
					if (currentLength>maxLength) {
						maxLength = currentLength;
					}
				}
			}
			return maxLength;
		}
	}

	private int m; //# of rows
	private int n; //# of columns
	private int[][] matrix;

	//main method for testing
	public static void main(String[] args) {
		System.out.print("Input matrix A. ");
		Matrix A = new Matrix();
		System.out.println("Matrix A is: \n"+A.toString()+"\n");
		
		System.out.print("Input matrix B. ");
		Matrix B = new Matrix();
		System.out.println("Matrix B is: \n"+B.toString()+"\n"+"AB = ");
		try {
			System.out.println(A.multiplies(B));			
		} catch (IncompatibleMatrixException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}

	}

	public Matrix() {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("To create an m * n matrix, please enter m and n:");
		m=keyboard.nextInt();
		n=keyboard.nextInt();
		matrix = new int[m][n];
		System.out.println("now, enter the rows of the matrix:");

		for (int i=0; i<m; i++) { //ith row
			for (int j=0; j<n; j++) { //jth column
				matrix[i][j]=keyboard.nextInt();
			}
		}
	}

	public Matrix(int m, int n, int[][] matrix) {
		this.m=m;
		this.n=n;
		this.matrix=matrix;
	}

	public int getAij(int i, int j) {
		return matrix[i][j];
	} 
	public int getm() {
		return m;
	}
	public int getn() {
		return n;
	}

	public Matrix multiplies (Matrix B) throws IncompatibleMatrixException {
		int newm=this.m;
		int oldn=this.n;
		int newn=B.getn();
		int sum=0;

		if (oldn!=B.getm()) {
			throw new IncompatibleMatrixException(this,B);
		}

		int[][] returnMatrix = new int[newm][newn];
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

	
	public String toString() {
		String s="";
		int maxLength = Help.entryMaxLength(this); //can you do that?
		
		int columnMaxLength = 0;
		int currentColumnLength = 0;
		for (int i=0; i<m; i++) {
			currentColumnLength = Integer.toString(matrix[i][0]).length();
			if (currentColumnLength > columnMaxLength) {
				columnMaxLength = currentColumnLength;
			}
		}

		s+=" _";
		for (int i=0; i<((maxLength+1)*(n-1)+columnMaxLength+2); i++) {
			s+=" ";
		}
		s+="_\n";

		for (int i=0; i<m; i++) { //(i-1)th row
			if (i!=(m-1)) {
				s+="|  ";
			} else {
				s+="|_ ";
			}
			for (int j=0; j<n; j++) { //(j-1)th column
				if (j!=0) {
					s+=Help.leftFill(Integer.toString(matrix[i][j]),maxLength+1," ");
				} else {
					s+=Help.leftFill(Integer.toString(matrix[i][j]),columnMaxLength," ");
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