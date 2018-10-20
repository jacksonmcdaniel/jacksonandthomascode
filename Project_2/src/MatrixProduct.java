
/*
 * Thomas Bramble trbrambl
 * Jackson McDaniel jkmcdani
 * 10/19/2018
 * Project 2
 */
public class MatrixProduct {
    public static int[][] matrixProduct_DAC(int[][] A, int[][] B) throws IllegalArgumentException {
        int rowA = A.length;
        int colA = A[0].length;
        int rowB = B.length;
        int colB = B[0].length;
        int n = rowA;
        int C[][] = new int[A.length][A.length];
        if (rowA != rowB || colA != colB || rowA != colA || rowB != colB)
            throw new IllegalArgumentException();
        if (!isPowerOfTwo(n))
            throw new IllegalArgumentException();

        C = matrixProduct_Recurse(A, 0, 0, B, 0, 0, n);

        return C;
    }

    private static boolean isPowerOfTwo(int n) {
        while (((n % 2) == 0) && n > 1) {
            n = n / 2;
        }
        if (n == 1)
            return true;
        else
            return false;
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
            int[][] C11 = matrixSum(matrixProduct_Recurse(A, startRowA, startColA, B, startRowB, startColB, halfN), matrixProduct_Recurse(A, startRowA, startColA+halfN, B, startRowB+halfN, startColB, halfN));
            int[][] C12 = matrixSum(matrixProduct_Recurse(A, startRowA, startColA, B, startRowB, startColB + halfN, halfN), matrixProduct_Recurse(A, startRowA, startColA+halfN, B, startRowB+halfN, startColB+halfN, halfN));
            int[][] C21 = matrixSum(matrixProduct_Recurse(A, startRowA + halfN, startColA, B, startRowB, startColB, halfN), matrixProduct_Recurse(A, startRowA+halfN, startColA+halfN, B, startRowB+halfN, startColB, halfN));
            int[][] C22 = matrixSum(matrixProduct_Recurse(A, startRowA + halfN, startColA, B, startRowB, startColB + halfN, halfN), matrixProduct_Recurse(A, startRowA+halfN, startColA+halfN, B, startRowB+halfN, startColB+halfN, halfN));

            int k = 0, l = 0;
            for (int i = 0; i < halfN; i++) {
                for (int j = 0; j < halfN; j++) {
                    C[k][l] = C11[i][j];
                    l++;
                }
                l = 0;
                k++;
            }

            k = 0;
            l = halfN;
            for (int i = 0; i < halfN; i++) {
                for (int j = 0; j < halfN; j++) {
                    C[k][l] = C12[i][j];
                    l++;
                }
                l = halfN;
                k++;
            }

            k = halfN;
            l = 0;
            for (int i = 0; i < halfN; i++) {
                for (int j = 0; j < halfN; j++) {
                    C[k][l] = C21[i][j];
                    l++;
                }
                l = 0;
                k++;
            }

            k = halfN;
            l = halfN;
            for (int i = 0; i < halfN; i++) {
                for (int j = 0; j < halfN; j++) {
                    C[k][l] = C22[i][j];
                    l++;
                }
                l = halfN;
                k++;
            }

        }

        return C;
    }

    public static int[][] matrixProduct_Strassen(int[][] A, int[][] B) throws IllegalArgumentException {
        int rowA = A.length;
        int colA = A[0].length;
        int rowB = B.length;
        int colB = B[0].length;
        int n = rowA;
        int C[][] = new int[A.length][A.length];
        if (rowA != rowB || colA != colB || rowA != colA || rowB != colB)
            throw new IllegalArgumentException();
        if (!isPowerOfTwo(n))
            throw new IllegalArgumentException();


        C = matrixProduct_Recurse_S(A, 0, 0, B, 0, 0, n);

        return C;
    }

    private static int[][] matrixSum(int[][] A, int startRowA, int startColA, int[][] B, int startRowB, int startColB, int n) {
        int[][] C = new int[n][n];

        int t = 0;
        int s = 0;
        int k = startRowB;
        int l = startColB;
        for (int i = startRowA; i < startRowA + n; i++, k++, s++) {
            for (int j = startColA; j < startColA + n; j++, l++, t++) {
                C[s][t] = A[i][j] + B[k][l];
            }
            l = startColB;
            t = 0;
        }
        return C;
    }

    private static int[][] matrixSubtract(int[][] A, int startRowA, int startColA, int[][] B, int startRowB, int startColB, int n) {
        int[][] C = new int[n][n];

        int t = 0;
        int s = 0;
        int k = startRowB;
        int l = startColB;
        for (int i = startRowA; i < startRowA + n; i++, k++, s++) {
            for (int j = startColA; j < startColA + n; j++, l++, t++) {
                C[s][t] = A[i][j] - B[k][l];
            }
            l = startColB;
            t = 0;
        }
        return C;
    }

    private static int[][] matrixProduct_Recurse_S(int[][] A, int startRowA, int startColA, int[][] B, int startRowB, int startColB, int n) {
        int[][] C = new int[n][n];

        int halfN = n/2;
        if (n == 1)
            C[0][0] = A[startRowA][startColA] * B[startRowB][startColB];
        else {

            int[][] S1 = matrixSubtract(B, startRowB, startColB + halfN, B, startRowB + halfN, startColB + halfN, halfN);
            int[][] S2 = matrixSum(A, startRowA, startColA, A, startRowA, startColA + halfN, halfN);
            int[][] S3 = matrixSum(A, startRowA + halfN, startColA, A, startRowA + halfN, startColA + halfN, halfN);
            int[][] S4 = matrixSubtract(B, startRowB + halfN, startColB, B, startRowB, startColB, halfN);
            int[][] S5 = matrixSum(A, startRowA, startColA, A, startRowA + halfN, startColA + halfN, halfN);
            int[][] S6 = matrixSum(B, startRowB, startColB, B, startRowB + halfN, startColB + halfN, halfN);
            int[][] S7 = matrixSubtract(A, startRowA, startColA + halfN, A, startRowA + halfN, startColA + halfN, halfN);
            int[][] S8 = matrixSum(B, startRowB + halfN, startColB, B, startRowB + halfN, startColB + halfN, halfN);
            int[][] S9 = matrixSubtract(A, startRowA, startColA, A, startRowA + halfN, startColA, halfN);
            int[][] S10 = matrixSum(B, startRowB, startColB, B, startRowB, startColB + halfN, halfN);

            int[][] P1 = matrixProduct_Recurse_S(A, startRowA, startColA, S1, 0, 0, halfN);
            int[][] P2 = matrixProduct_Recurse_S(S2, 0, 0, B, startRowB + halfN, startColB + halfN, halfN);
            int[][] P3 = matrixProduct_Recurse_S(S3, 0, 0, B, startRowB, startColB, halfN);
            int[][] P4 = matrixProduct_Recurse_S(A, startRowA + halfN, startColA + halfN, S4, 0, 0, halfN);
            int[][] P5 = matrixProduct_Recurse_S(S5, 0, 0, S6, 0, 0, halfN);
            int[][] P6 = matrixProduct_Recurse_S(S7, 0, 0, S8, 0, 0, halfN);
            int[][] P7 = matrixProduct_Recurse_S(S9, 0, 0, S10, 0, 0, halfN);

            int[][] temp = matrixSum(P5, 0, 0, P4, 0, 0, halfN);
            int[][] temp2 = matrixSubtract(temp, 0, 0, P2, 0, 0, halfN);
            int[][] C11 = matrixSum(temp2, 0, 0, P6, 0, 0, halfN);

            int[][] C12 = matrixSum(P1, 0, 0, P2, 0, 0, halfN);

            int[][] C21 = matrixSum(P3, 0, 0, P4, 0, 0, halfN);

            temp = matrixSum(P5, 0, 0, P1, 0, 0, halfN);
            temp2 = matrixSubtract(temp, 0, 0, P3, 0, 0, halfN);
            int[][] C22 = matrixSubtract(temp2, 0, 0, P7, 0, 0, halfN);

            int k = 0, l = 0;
            for (int i = 0; i < halfN; i++) {
                for (int j = 0; j < halfN; j++) {
                    C[k][l] = C11[i][j];
                    l++;
                }
                l = 0;
                k++;
            }

            k = 0;
            l = halfN;
            for (int i = 0; i < halfN; i++) {
                for (int j = 0; j < halfN; j++) {
                    C[k][l] = C12[i][j];
                    l++;
                }
                l = halfN;
                k++;
            }

            k = halfN;
            l = 0;
            for (int i = 0; i < halfN; i++) {
                for (int j = 0; j < halfN; j++) {
                    C[k][l] = C21[i][j];
                    l++;
                }
                l = 0;
                k++;
            }

            k = halfN;
            l = halfN;
            for (int i = 0; i < halfN; i++) {
                for (int j = 0; j < halfN; j++) {
                    C[k][l] = C22[i][j];
                    l++;
                }
                l = halfN;
                k++;
            }

        }

        return C;
    }
}