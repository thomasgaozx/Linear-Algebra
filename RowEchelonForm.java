public class RowEchelonForm { // a static class

	//Main function for testing
	public static void main(String[] args) {
	}

	//major methods
	public static boolean isREF(Matrix M) {
		int i, j=0; //current row and column
		int headRow=0;
		int m=M.getm(), n=M.getn();
		while (headRow<m && j<n) {
			i=headRow;
			i++;
			while (i<m) {
				if (M.getAij(i,j)!=0) {
					return false;
				}
				i++;
			}
			if (M.getAij(headRow,j) != 0) {
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

		boolean rankPlusOne=false;

		while (j<n && i<m-1) { // could do i<m-1, but then the rank will be incorrect
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

		int lastRow=m-1; //last row

		while (j<n) {
			if (M.getAij(lastRow, j)!=0) {
				rankPlusOne=true;
			}
			j++;
		}
		if (rankPlusOne) {
			rank++;
		}

		return rank;
	}
}