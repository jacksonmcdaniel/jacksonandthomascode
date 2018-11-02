/*
Jackson McDaniel jkmcdani@calpoly.edu
Thomas Bramble trbrambl@calpoly.edu
11-2-2018
Project 3
*/

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;

public class GameProblem {

    public static void main(String[] args) throws FileNotFoundException{
		System.out.print("Enter file name: ");
		Scanner sc = new Scanner(System.in);
		File file;
		String fileName = sc.nextLine();
		file = new File(fileName);
		
        sc = new Scanner(file);
        
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] A = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                A[i][j] = sc.nextInt();
            }
        }
    
        game(n, m, A);
    }

    public static void game(int n, int m, int[][] A) {
        int[][] S = new int[n][m];
        char[][] R = new char[n][m];
        int x = -1;
        int y = -1;

        for (int i = n-1; i >= 0; i--) {
            for (int j = m-1; j >= 0; j--) {
                if (i == n-1 && j == m-1) {
                    S[i][j] = A[i][j];
                    R[i][j] = 'e';
                    x = i;
                    y = j;
                }
                else if (j == m - 1) {
                    if (S[i + 1][m - 1] > 0) {
                        S[i][j] = S[i + 1][j] + A[i][j];
                        R[i][j] = 'd';
                    } else {
                        S[i][j] = A[i][j];
                        R[i][j] = 'e';
                    }
                }
                else if (i == n - 1) {
                    if (S[n - 1][j + 1] > 0) {
                        S[i][j] = S[i][j + 1] + A[i][j];
                        R[i][j] = 'r';
                    } else {
                        S[i][j] = A[i][j];
                        R[i][j] = 'e';
                    }
                }
                else {
                    if (S[i + 1][j] > S[i][j + 1]) {
                        S[i][j] = S[i + 1][j] + A[i][j];
                        R[i][j] = 'd';
                    } else {
                        S[i][j] = S[i][j + 1] + A[i][j];
                        R[i][j] = 'r';
                    }
                }
                if (S[i][j] > S[x][y]) {
                    x = i;
                    y = j;
                }
            }
        }
        
        System.out.println("Best score: " + S[x][y]);

        System.out.printf("Best route: ");
        while (R[x][y] != 'e') {
            System.out.printf("[%d,%d] to ", x + 1, y + 1);
            if (R[x][y] == 'd') {
                x++;
            } else {
                y++;
            }
        }
        System.out.printf("[%d,%d] to ", x + 1, y + 1);
        System.out.printf("exit\n");

    }
}