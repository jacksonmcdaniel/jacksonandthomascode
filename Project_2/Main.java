import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		String fileName; 
    	Scanner sc = new Scanner(System.in);
    		
    	System.out.print("Enter file name: ");
    	
    	fileName = sc.nextLine();
    	File file = new File("C:\\Users\\trbra\\csc349\\jacksonandthomascode\\Project_2\\testing\\" + fileName);
    	
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
    	
    	
    	try {
    		int[][] C = MatrixProduct.matrixProduct_DAC(A, B);
    	
    	
	        System.out.println("Product matrix:");
	        for (int i = 0; i < C.length; i++) {
	    		for (int j = 0; j < C[0].length; j++) {
	    			System.out.print(C[i][j] + " ");
	    		}
	    		System.out.println();
	    	}
    	}
        catch (IllegalArgumentException e) {
    		System.out.println("Matrices are incompatible.");
    	}
    }		

	}


