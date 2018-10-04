import java.util.Random;
import static java.lang.System.nanoTime;

public class SortCounts {

    public static void main(String[] args) {
        long averageSelectionCount;
        long averageMergeCount;
        long averageQuickCount;

        int N = 100;
        Random rand = new Random();
        // Initialize Arraylists for each sorting algorithm

        for (int i = N; i <= 12800; i *= 2) { 
            int[] arrSS = new int[N];
            int[] arrMS = new int[N];
            int[] arrQS = new int[N];

            long selectionCounts = 0;
            long mergeCounts = 0;
            long quickCounts = 0;
            
            for (int k = 0; k < 100; k++) {

                for (int j = 0; j < N; j++) {
                    int next = rand.nextInt(1000);
                    arrSS[j] = next;
                    arrMS[j] = next;
                    arrQS[j] = next;
                }

                selectionCounts += Sorts1.selectionSort(arrSS, N);
                mergeCounts += Sorts1.mergeSort(arrMS, N);
                quickCounts += Sorts1.quickSort(arrQS, N);

            }
            
            averageSelectionCount = selectionCounts / 100;
            averageMergeCount = mergeCounts / 100;
            averageQuickCount = quickCounts / 100;

            System.out.printf("N=%d: C_ss=%d, C_ms=%d, C_qs=%d\n", N, averageSelectionCount, averageMergeCount, averageQuickCount);

            N *= 2;
        }
    }
}