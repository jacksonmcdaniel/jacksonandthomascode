/* Jackson McDaniel - jkmcdani@calpoly.edu
Thomas Bramble - trbrambl@calpoly.edu
Project 1 - 10/05/2018 */

public class Main {

	public static void main(String[] args) {
		int[] arr = {4, 6, 3, 2, 5, 7, 7, 5, 4, 3, 5, 6, 7, 8, 9, 2, 34, 5, 4, 3, 2, 6, 4, 5};
		int N = arr.length;
		for (int i = 0; i < N; i++) {
			System.out.printf("%d ", arr[i]);
		}
		System.out.println();
		Sorts.quickSort(arr, N);
		for (int i = 0; i < N; i++) {
			System.out.printf("%d ", arr[i]);
		}
	}

}
