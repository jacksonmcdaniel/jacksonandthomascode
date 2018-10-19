import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MatrixWork {

    public static void main(String[] args) throws FileNotFoundException {
    	String fileName; 
    	Scanner sc = new Scanner(System.in);
    		
    	System.out.print("Enter file name: ");
    	
    	fileName = sc.nextLine();
    	File file = new File("./testing/" + fileName);
    	
    	sc = new Scanner(file);
    	
    	int row, column;
    	
    	row = sc.nextInt();
    	column = sc.nextInt();
    	int[][] A = new int[row][column];
    	
    	for (int i = 0; i < row; i++) {
    		for (int j = 0; j < column; j++) {
    			A[i][j] = sc.nextInt();
    		}
    	}
    	
    	row = sc.nextInt();
    	column = sc.nextInt();
    	int[][] B = new int[row][column];
    	
    	for (int i = 0; i < row; i++) {
    		for (int j = 0; j < column; j++) {
    			B[i][j] = sc.nextInt();
    		}
    	}
    	
    	
    	
        int[][] C = matrixProduct(A, B);
    	
        System.out.println("Product matrix:");
        for (int i = 0; i < C.length; i++) {
    		for (int j = 0; j < C[0].length; j++) {
    			System.out.print(C[i][j] + " ");
    		}
    		System.out.println();
    	}
        
    }

    public static int[][] matrixProduct(int[][] A, int[][] B) throws IllegalArgumentException {
        int n = A.length;
        int k = B.length;
        int m = B[0].length;
        
        if (A[0].length != k) 
        	throw new IllegalArgumentException();
        int[][] C = new int[n][m];
        
        for (int i = 0; i < n; i++) {
        	for (int j = 0; j < m; j++) {
        		for (int l = 0; l < k; l++) {
        			C[i][j] = C[i][j] + A[i][l] * B[l][j];
        		}
        	}
        }
        
        return C;

    }

}
