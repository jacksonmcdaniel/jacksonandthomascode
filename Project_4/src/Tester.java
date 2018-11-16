/*
Jackson McDaniel - jkmcdani@calpoly.edu
Thomas Bramble - trbrambl@calpoly.edu
Project 4 - 11/14/2018
*/

public class Tester {

    public static void main(String args[]) {
        int[] US = {100, 50, 25, 10, 5, 1};
        int[] Soviet = {100, 50, 20, 15, 10, 5, 3, 2, 1};
        int[] powersOfTwo = {64, 32, 16, 8, 4, 2, 1};
        int[] USNoNickel = {100, 50, 25, 10, 1};
        int[] set = {66, 35, 27, 18, 10, 1};

        int testResultsUS = 0;
        int testResultsSoviet = 0;
        int testResultsPowers = 0;
        int testResultsUSNoNickel = 0;
        int testResultsSet = 0;

        int[] DPResult;
        int[] greedyResult;

        for (int i = 1; i < 201; i++) {
            DPResult = ChangeMaker.change_DP(i, US);
            greedyResult = ChangeMaker.change_greedy(i, US);
            if (compareResults(DPResult, greedyResult)) {
                testResultsUS++;
            }

            DPResult = ChangeMaker.change_DP(i, Soviet);
            greedyResult = ChangeMaker.change_greedy(i, Soviet);
            if (compareResults(DPResult, greedyResult)) {
                testResultsSoviet++;
            }

            DPResult = ChangeMaker.change_DP(i, powersOfTwo);
            greedyResult = ChangeMaker.change_greedy(i, powersOfTwo);
            if (compareResults(DPResult, greedyResult)) {
                testResultsPowers++;
            }

            DPResult = ChangeMaker.change_DP(i, USNoNickel);
            greedyResult = ChangeMaker.change_greedy(i, USNoNickel);
            if (compareResults(DPResult, greedyResult)) {
                testResultsUSNoNickel++;
            }

            DPResult = ChangeMaker.change_DP(i, set);
            greedyResult = ChangeMaker.change_greedy(i, set);
            if (compareResults(DPResult, greedyResult)) {
                testResultsSet++;
            }
        }
        System.out.println("Testing change_DP and change_greedy algorithms");
        System.out.println("Testing set1: " + testResultsUS + " matches in 200 tests");
        System.out.println("Testing set2: " + testResultsSoviet + " matches in 200 tests");
        System.out.println("Testing set3: " + testResultsPowers + " matches in 200 tests");
        System.out.println("Testing set4: " + testResultsUSNoNickel + " matches in 200 tests");
        System.out.println("Testing set5: " + testResultsSet + " matches in 200 tests");
            
            

    }


        private static boolean compareResults(int[] DPResult, int[] greedyResult) {
            int DP = 0;
            int greedy = 0;

            for (int num : DPResult) {
                DP += num;
            }

            for (int num : greedyResult) {
                greedy += num;
            }

            return DP == greedy;
        }
}
