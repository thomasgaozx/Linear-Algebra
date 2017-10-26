public class MatrixIncompatibleException extends Exception {

	private static class MatrixPrint {
		public static String getString (Matrix[] MatrixArray) {
			String s = "";
			for (Matrix M : MatrixArray) {
				s+=M.toString()+"\n";
			}
			return s;
		}
	}

	public MatrixIncompatibleException() {
		super("The matrices are not compatible!");
	}

	public MatrixIncompatibleException(String message) {
		super(message);
	}

	public MatrixIncompatibleException(Matrix ... MatrixArray) {
		super("The matrices: \n" + MatrixPrint.getString(MatrixArray) + "are not compatible.");
	}

	public MatrixIncompatibleException(Matrix M, Matrix N) {
		super("You cannot multiply a "+M.getm()+" * "+M.getn()+" matrix to a "+N.getm()+" * " + N.getn() + "matrix.");
	}
}