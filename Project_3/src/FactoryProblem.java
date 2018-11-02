/*
Jackson McDaniel jkmcdani@calpoly.edu
Thomas Bramble trbrambl@calpoly.edu
11-2-2018
Project 3
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FactoryProblem {

	public static void main(String[] args) throws FileNotFoundException{
		System.out.print("Enter file name: ");
		Scanner sc = new Scanner(System.in);
		File file;
		String fileName = sc.nextLine();
		file = new File(fileName);
		
		
		
		sc = new Scanner(file);
		int n = sc.nextInt();
		int e1 = sc.nextInt();
		int e2 = sc.nextInt();
		int x1 = sc.nextInt();
		int x2 = sc.nextInt();
		
		int [] A1 = new int[n];
		for (int i = 0; i < n; i++)
			A1[i] = sc.nextInt();
		int [] A2 = new int[n];
		for (int i = 0; i < n; i++)
			A2[i] = sc.nextInt();
		
		int [] T1 = new int[n-1];
		for (int i = 0; i < n-1; i++)
			T1[i] = sc.nextInt();
		int [] T2 = new int[n-1];
		for (int i = 0; i < n-1; i++)
			T2[i] = sc.nextInt();
		assemblyCalculation(n, e1, e2, x1, x2, A1, A2, T1, T2);
	}
	
	private static void assemblyCalculation(int n, int e1, int e2, int x1, int x2, int [] A1, int [] A2, int [] T1, int [] T2) {
		int [] F1 = new int [n];
		int [] F2 = new int [n];
		int [] L1 = new int [n-1];
		int [] L2 = new int [n-1];
		int solution, finalL;
		
		F1[0] = e1 + A1[0];
		F2[0] = e2 + A2[0];
		
		for (int i = 0; i < n-1; i++) {
			if (F1[i] <= F2[i] + T2[i]) {
				F1[i+1] = F1[i] + A1[i+1];
				L1[i] = 1;
			}
			else {
				F1[i+1] = F2[i] + A1[i+1] + T2[i];
				L1[i] = 2;
			}
			if (F2[i] <= F1[i] + T1[i]) {
				F2[i+1] = F2[i] + A2[i+1];
				L2[i] = 2;
			}
			else {
				F2[i+1] = F1[i] + A2[i+1] + T1[i];
				L2[i] = 1;
			}
				
		}
		
		if (F1[n-1] + x1 <= F2[n-1] + x2) {
			solution = F1[n-1] + x1;
			finalL = 1;
		}
		else {
			solution = F2[n-1] + x2;
			finalL = 2;
		}
		
		System.out.println();
		System.out.println("Fastest time is: " + solution);
		System.out.println();
		
		System.out.println("The optimal route is:");
		assemblyPrint(L1, L2, finalL);
		
	}
	
	private static void assemblyPrint(int [] L1, int [] L2, int finalL) {
		int [] S = new int [L1.length+1];
		int [] P = new int [L1.length+1];
		
		P[L1.length] = finalL;
			
				
		for (int i = L1.length-1; i >= 0; i--) {
			if (P[i+1] == 1)
				P[i] = L1[i];
			else
				P[i] = L2[i];
		}
		int p = 1;
		for (int i = 0; i < L1.length+1; i++) {
			System.out.println("station " + p + " line " + P[i]);
			p++;
		}
	}

}
