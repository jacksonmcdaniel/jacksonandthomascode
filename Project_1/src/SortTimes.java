/* Jackson McDaniel - jkmcdani@calpoly.edu
Thomas Bramble - trbrambl@calpoly.edu
Project 1 - 10/05/2018 */

import java.util.Random;

import static java.lang.System.nanoTime;

class SortTimes {

    public static void main(String[] args) {
        int N = 5000;
        Random rand = new Random();
        
        int[] arrSS = new int[160000];
        int[] arrMS = new int[160000];
        int[] arrQS = new int[160000];

        for (int i = 0; i < 6; i++) {    

            for (int k = 0; k < 5; k++) {
                
                for (int j = 0; j < N; j++) {
                    int next = rand.nextInt(N);
                    arrSS[j] = next;
                    arrMS[j] = next;
                    arrQS[j] = next;
                }

                long startTimeSS = nanoTime();
                Sorts.selectionSort(arrSS, N);
                long endTimeSS = nanoTime();

                long startTimeMS = nanoTime();
                Sorts.mergeSort(arrMS, N);
                long endTimeMS = nanoTime();

                long startTimeQS = nanoTime();
                Sorts.quickSort(arrQS, N);
                long endTimeQS = nanoTime();

                long timeSS = (endTimeSS - startTimeSS) / 1000000;
                long timeMS = (endTimeMS - startTimeMS) / 1000000;
                long timeQS = (endTimeQS - startTimeQS) / 1000000;
                System.out.printf("N=%d: T_ss=%d, T_ms=%d, T_qs=%d\n", N, timeSS, timeMS, timeQS);
            }
            System.out.println();

            N = N * 2;
        }
    }
}