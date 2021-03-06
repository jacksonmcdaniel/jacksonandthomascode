/*
Jackson McDaniel - jkmcdani@calpoly.edu
Thomas Bramble - trbrambl@calpoly.edu
Project 4 - 11/14/2018
*/

import java.util.Arrays;
import java.util.Scanner;

public class ChangeMaker {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = 1;

        System.out.println("Enter the number of coin-denominations and the set of coin values:");
        int k = sc.nextInt();
        int[] d = new int[k];
        for (int i = 0; i < k; i++) {
            d[i] = sc.nextInt();
        }

        int[] B;
        int[] R;
        int temp;
        boolean trigger;
        while (n > 0) {
            trigger = false;
            temp = 0;
            System.out.println("Enter a positive amount to be changed (enter 0 to quit): ");
            n = sc.nextInt();
            if (n > 0) {
                B = change_DP(n, d);
                System.out.println();
                System.out.println("DP algorithm results");
                System.out.println("Amount: " + n);
                System.out.print("Optimal distribution: ");

                for (int i = 0; i < B.length; i++) {
                    if (B[i] > 0) {
                        if (trigger) {
                            System.out.print(" + " + B[i] + "*" + d[i] + "c");
                            temp += B[i];
                        } 
                        else {
                            System.out.print(B[i] + "*" + d[i] + "c");
                            temp += B[i];
                            trigger = true;
                        }
                    }
                }
                System.out.println();
                System.out.println("Optimal coin count: " + temp);
                System.out.println();

                trigger = false;
                temp = 0;

                R = change_greedy(n, d);
                System.out.println("Greedy algorithm results");
                System.out.println("Amount: " + n);
                System.out.print("Optimal distribution: ");

                for (int i = 0; i < R.length; i++) {
                    if (R[i] > 0) {
                        if (trigger) {
                            System.out.print(" + " + R[i] + "*" + d[i] + "c");
                            temp += R[i];
                        } 
                        else {
                            System.out.print(R[i] + "*" + d[i] + "c");
                            temp += R[i];
                            trigger = true;
                        }
                    }
                }
                System.out.println();
                System.out.println("Optimal coin count: " + temp);
                System.out.println();
            }
        }
        System.out.println("Thanks for playing. Good Bye.");
        
    }

    public static int[] change_DP(int n, int[] d) {
        int[] C = new int[n + 1];
        int[] A = new int[n + 1];
        
        C[0] = 0;
        A[0] = 0;

        for (int j = 1; j < n + 1; j++) {
            Integer temp = new Integer(Integer.MAX_VALUE);
            for (int i = 0; i < d.length; i++) {
                if (j >= d[i]) {
                    if (C[j-d[i]] < temp) {
                        C[j] = C[j-d[i]] + 1;
                        A[j] = i;
                        temp = C[j-d[i]];
                    }
                }
            }
        }

        int[] B = new int[d.length];
        int currentN = n;
        int temp;
        for (int i = 0; i < C[C.length-1]; i++) {
            temp = A[currentN];
            B[temp] += 1;
            currentN = currentN - d[temp];
        }

        return B;
    }

    public static int[] change_greedy(int n, int[] d) {
        int[] results = new int[d.length];
        int dIndex = 0;
        int resultsIndex = 0;

        while (n > 0) {
            if (n >= d[dIndex]) {
                n = n - d[dIndex];
                results[resultsIndex]++;
            } else {
                dIndex++;
                resultsIndex++;
            }
        }
        return results;
    }
}