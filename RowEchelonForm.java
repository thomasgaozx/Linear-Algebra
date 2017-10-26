public class RowEchelonForm extends Matrix {

	//instance variables
	private int rank;
	private Matrix REF;

	//Main function for testing
	public static void main(String[] args) {
		Matrix A = new Matrix();
		System.out.print("The claim that matrix \n" + A.toString() + "\n\nis in REF is ");
		System.out.println(isREF(A));
	}

	//Constructor

	public RowEchelonForm(Matrix M) {
		this.REF=M; //.clone() method required LATER
		this.rank = reduceToREF(REF);
	}

	//get methods
	public double getAij(int i, int j) { //overriden
		return REF.getAij(i,j);
	} 
	public int getRank() {
		return rank;
	}

	//major methods
	public static boolean isREF(Matrix M) {
		int i, j=0; //current row and column
		int headRow=0;
		int m=M.getm(), n=M.getn();
		while (headRow<m && j<n) {
			i=headRow;
			if (M.getAij(headRow,j) == 0) {
				i++;
				while(i<m) {
					if (M.getAij(i,j)!=0) {
						return false;
					}
					i++;
				}
			} else {
				i++;
				while (i<m) {
					if (M.getAij(i,j)!=0) {
						return false;
					}
					i++;
				}
				headRow++;
			}
			j++;
		}
		return true;
	}

	public static int reduceToREF(Matrix M) { // return rank
		int m=M.getm(), n=M.getn();

		int i=0, j=0; //current row and column 
		int headRow; //index of the first row of the submatrix during each operation

		int rank=0;

		double pivot; //a value that remains constant during each column iteration
		double leadingTerm; //a value that changes during each column iteration

		while (j<n && i<m-1) {
			headRow=i;
			while (i<m && M.getAij(i,j)==0) {
				i++;
			}
			if (i!=m) {
				rank++;
				pivot = M.getAij(i,j); //just a value
				if (i!=headRow) {
					M.swap(i,headRow);
				}
				i++;
				while (i<m) {
					leadingTerm = M.getAij(i,j);
					if (leadingTerm != 0) {
						M.addToRow(i,headRow,(-leadingTerm/pivot));
					}
					i++;
				}
				i=headRow+1; //reset i with 1 increment
			} else {
				i=headRow;
			}
			j++;
		}
		return rank;
	}
}