public class MatrixWork {

    public static void main(String[] args) {

    }

    public static int[][] matrixProduct(int[][] A, int[][] B) throws IllegalArgumentException {
        int[][] returnMatrix = new int[A.length][B[0].length];
        int aRows = A.length;
        int aCols = A[0].length;
        int bRows = B.length;
        int bRows = B[0].length;
        
        if (aCols != bRows) {
            throw IllegalArgumentException();
        }

        for (int i = 0; i < aRows; i++) {
            for (int j = 0; j < bCols; j++) {
                
            }
        }

    }

    private static int dotProduct(int[] row, int[] col) {
        int result = 0;
        for (int i = 0; i < row.length; i++) {
            result += row[i] * col[i]; 
        }
        return result;
    }
}