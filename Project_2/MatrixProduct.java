
public class MatrixProduct {
	public static int[][] matrixProduct_DAC(int[][] A, int[][] B) throws IllegalArgumentException {
		int rowA = A.length;
		int colA = A[0].length;
		int rowB = B.length;
		int colB = B[0].length;
		int n = rowA;
		int C[][] = new int[A.length][A.length];
		try {
			if (rowA != rowB || colA != colB || rowA != colA || rowB != colB)
				throw new IllegalArgumentException();
			if (n % 2 != 0)
				throw new IllegalArgumentException();
			
			C = matrixProduct_Recurse(A, 0, 0, B, 0, 0, n);
		}
		
		catch (IllegalArgumentException e) {
			System.out.println("Invalid Matrices!");
		}
		
		return C;
	}
	
	
	private static int[][] matrixSum(int[][] A, int[][] B) {
		int [][] C = new int[A.length][A.length];
		
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A.length; j++) {
				C[i][j] = A[i][j] + B[i][j];
			}
		}
		return C;
	}
	
	private static int[][] matrixProduct_Recurse(int[][] A, int startRowA, int startColA, int[][] B, int startRowB, int startColB, int n) {
		int[][] C = new int[n][n];
	
		int halfN = n/2;
		if (n == 1)
			C[0][0] = A[startRowA][startColA] * B[startRowB][startColB];
		else {
			int[][] C11 = matrixSum(matrixProduct_Recurse(A, 0, 0, B, 0, 0, halfN), matrixProduct_Recurse(A, 0, halfN, B, halfN, 0, halfN));
			int[][] C12 = matrixSum(matrixProduct_Recurse(A, 0, 0, B, 0, halfN, halfN), matrixProduct_Recurse(A, 0, halfN, B, halfN, halfN, halfN));
			int[][] C21 = matrixSum(matrixProduct_Recurse(A, halfN, 0, B, 0, 0, halfN), matrixProduct_Recurse(A, halfN, halfN, B, halfN, 0, halfN));
			int[][] C22 = matrixSum(matrixProduct_Recurse(A, halfN, 0, B, 0, halfN, halfN), matrixProduct_Recurse(A, halfN, halfN, B, halfN, halfN, halfN));
		
			int k = 0, l = 0;
			for (int i = 0; i < halfN; i++) {
				for (int j = 0; j < halfN; j++) {
					C[k][l] = C11[i][j];
					l++;
				}
				k++;
			}
			
			k = 0;
			l = halfN;
			for (int i = 0; i < halfN; i++) {
				for (int j = 0; j < halfN; j++) {
					C[k][l] = C12[i][j];
					l++;
				}
				k++;
			}
			
			k = halfN;
			l = 0;
			for (int i = 0; i < halfN; i++) {
				for (int j = 0; j < halfN; j++) {
					C[k][l] = C21[i][j];
					l++;
				}
				k++;
			}
			
			k = halfN;
			l = halfN;
			for (int i = 0; i < halfN; i++) {
				for (int j = 0; j < halfN; j++) {
					C[k][l] = C22[i][j];
					l++;
				}
				k++;
			}
			
		}
		
		return C;
		
		
		
		
	}
	
	

}
