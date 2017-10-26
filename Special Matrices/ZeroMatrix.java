public class ZeroMatrix extends Matrix {
	private double[][] zeroArray(int ij) {
		double[][] returnArray = new double[ij][ij];
		for (int i=0; i<ij; i++) {
			for (int j=0; j<ij; j++) {
				returnArray[i][j]=0;
			}
		}
		return returnArray;
	}

	public IdentityMatrix(int n) {
		super(n,n,zeroArray(n));
	}
}