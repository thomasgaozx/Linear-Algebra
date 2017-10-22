public class IncompatibleMatrixException extends Exception {

	private static class MatrixPrint {
		public static String getString (Matrix[] MatrixArray) {
			String s = "";
			for (Matrix M : MatrixArray) {
				s+=M.toString()+"\n";
			}
			return s;
		}
	}

	public IncompatibleMatrixException() {
		super("The matrices are not compatible!");
	}

	public IncompatibleMatrixException(String message) {
		super(message);
	}

	public IncompatibleMatrixException(Matrix ... MatrixArray) {
		super("The matrices: \n" + MatrixPrint.getString(MatrixArray) + "are not compatible.");
	}

	public IncompatibleMatrixException(Matrix M, Matrix N) {
		super("You cannot multiply a "+M.getm()+" * "+M.getn()+" matrix to a "+N.getm()+" * " + N.getn() + "matrix.");
	}
}