
public class Main {

	public static void main(String[] args) {
		int[] arr = {5, 6, 3, 2, 5, 6, 7};
		int N = 6;
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
