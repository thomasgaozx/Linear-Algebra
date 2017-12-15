import java.util.Scanner;

class Shell {

	public static void main(String[] args) {
		Scanner keyboard=new Scanner(System.in);
		boolean exitProgram=false;
		String command="";
		int m=0, n=0;
		while (!exitProgram) {
			System.out.print("> ");
			command=keyboard.nextLine();
			if (command.equals("exit")) {
				exitProgram=true;
				System.out.println("program terminated.");
			} else if (command.equals("help")) {
				System.out.println("exit: exit program \nhelp: a list of keywords");
				System.out.println("solve: row reduce the matrix to Reduced Row Echelon Form");
				System.out.println("inverse: find the inverse of the matrix");
				System.out.println("rank: find the rank of the matrix");
				System.out.println("isREF: check whether a matrix is in Row Echelon Form or not");
				System.out.println("isRREF: check whether the matrix is in Reduced Row Echelon Form or not");
				System.out.println("isInvertible: check whether the matrix is invertible");

			} else if (command.equals("solve")) {
				Matrix A = new Matrix();
				System.out.println(A+"\n\n");
				ReducedRowEchelonForm.reduceToRREF(A);
				System.out.println(A);
			} else if (command.equals("inverse")) {
				Matrix A = new Matrix();
				System.out.println(A+"\n\n");
				A=A.inverse();
				System.out.println(A);
			} else if (command.equals("rank")) {
				Matrix A = new Matrix();
				System.out.println(A+"\n\n");
				System.out.println("the rank of the matrix is: "+RowEchelonForm.reduceToREF(A));
			} else if (command.equals("isREF")) {
				Matrix A = new Matrix();
				System.out.println(A+"\n\n");
				if (RowEchelonForm.isREF(A)) {
					System.out.println("the matrix is in Row Echelon Form");
				} else {
					System.out.println("the matrix is not in Row Echelon Form");
				}
			} else if (command.equals("isRREF")) {
				Matrix A = new Matrix();
				System.out.println(A+"\n\n");
				if (ReducedRowEchelonForm.isRREF(A)) {
					System.out.println("the matrix is in Reduced Row Echelon Form");
				} else {
					System.out.println("the matrix is not in Reduced Row Echelon Form");
				}				
			} else if (command.equals("isInvertible")) {
				Matrix A = new Matrix();
				System.out.println(A+"\n\n");
				if (A.isInvertible()) {
					System.out.println("the matrix is invertible");
				} else {
					System.out.println("the matrix is not invertible");
				}
			} else if (command.equals("")) {
				//do nothing				
			} else {
				System.out.println("the word " + command + " is not an internal keyword. Enter help for a list of keywords.");
			}

		}

		
	}
}