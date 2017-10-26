public class IdentityMatrix extends Matrix {
	private double[][] identityArray(int ij) {
		double[][] returnArray = new double[ij][ij];
		for (int t=0; t<ij; t++) {
			returnArray[i][j]=1;
		}
		return returnArray;
	}

	public IdentityMatrix(int n) {
		super(n,n,identityArray(n));
	}
}