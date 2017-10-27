public class ReducedRowEchelonForm extends Matrix {

	//instance variables
	private int rank;
	private Matrix RREF;

	//main function for testing
	public static void main(String[] args) {
		//Chemistry Kinetics and Reaction Rate Exam Question as a Test
		double[][] P=new double[3][4];
		P[0][0]=P[0][2]=Math.log(2);
		P[1][1]=P[2][2]=Math.log(4);
		P[1][0]=P[2][1]=Math.log(3);
		double r1=1.52E-7, r2=8.63E-7, r3=7.33E-6, r4=1.1E-5;
		P[0][3]=Math.log(r2/r1);
		P[1][3]=Math.log(r3/r1);
		P[2][3]=Math.log(r4/r1);
		Matrix A = new Matrix(3,4,P);
		System.out.println(A);
		reduceToRREF(A);
		System.out.println("\n"+A.toString());

		System.out.println(isRREF(A));
	}

	//Constructor
	public ReducedRowEchelonForm(Matrix M) {
		this.RREF=M; // a .clone method is to be implemented later
		this.rank = reduceToRREF(RREF);
	}

	//get methods
	public double getAij(int i, int j) {
		return RREF.getAij(i,j);
	}
	public int getRank() {
		return rank;
	}

	//major methods
	public static boolean isRREF(Matrix M) {
		int i=0, j=0;
		int headRow=0;
		int m=M.getm(), n=M.getn();
		while (headRow<m && j<n) {
			if (M.getAij(headRow,j)!=1 && M.getAij(headRow,j)!=0) {
				return false;
			}
			i=headRow;
			i++;
			while (i<m) {
				if (M.getAij(i,j)!=0) {
					return false;
				}
				i++;
			}

			if (M.getAij(headRow,j)==1) { //checking upper rows
				i=headRow;
				i--;
				while (i>0) {
					if (M.getAij(i,j)!=0) {
						return false;
					}
					i--;
				}
				headRow++;
			}
			j++;
		}
		return true;
	}

	public static int reduceToRREF(Matrix M) {
		int m=M.getm(), n=M.getn();

		int i=0, j=0;
		int headRow;

		int rank=0;

		double leadingTerm;

		while (j<n && i<m) {
			headRow=i;
			while (i<m && M.getAij(i,j)==0) {
				i++;
			}
			if (i!=m) {
				rank++; //no pivot values needed because it is already leading one
				M.scale(i,(1.0/M.getAij(i,j)));
				if (i!=headRow) {
					M.swap(i,headRow);
				}

				i++;

				//first loop clear all non-zero terms till end of column
				while (i<m) {
					leadingTerm = M.getAij(i,j);
					if (leadingTerm != 0) {
						M.addToRow(i,headRow, (-leadingTerm));
					}
					i++;
				}

				i=headRow;
				i--;

				//second loop clear all non-zero terms till begin of 
				while (i>=0) {
					leadingTerm = M.getAij(i,j);
					if (leadingTerm != 0) {
						M.addToRow(i,headRow, (-leadingTerm));
					}
					i--;
				}
				i=headRow+1; //reset for next iteration
			} else {
				i=headRow;
			}
			j++;
		}
		return rank;
	}
}