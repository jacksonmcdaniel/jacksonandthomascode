
public class Sorts {
	
	public static void selectionSort(int[] arr, int N) {
		int minIndex = 0;
		int temp;
		for (int i = 0; i < N; i++) {
			temp = arr[i];
			for (int j = i+1; j < N; j++) {
				if (arr[j] < temp) {
					minIndex = j;
					temp = arr[j];
				}
			}
			arr[minIndex] = arr[i];
			arr[i] = temp;
		}
	}
	
	public static void mergeSort(int[] arr, int N) {
		mergeSort(arr, 0, N-1);
	}
	
	private static void mergeSort (int[] list, int first, int last) {
		if (first < last) {
			int middle = (first + last)/2;
			mergeSort(list, first, middle);
			mergeSort(list, middle+1, last);
			mergeSortedHalves (list, first, middle, last); 
	    }
	}
	private static void mergeSortedHalves (int[] arr, int left, int middle, int right) {
		int[] temp = new int[right - left + 1];
		int index1 = left;
		int index2 = middle+1; 
		int index = 0;
		
		while (index1 <= middle && index2 <= right) {
			if (arr[index1] < arr[index2]) {
				temp[index] = arr[index1];
				index1++;
			}
			else {
				temp[index] = arr[index2];
				index2++;
			}
			index++;
		}
		if (index1 <= middle) {
			while (index1 <= middle) {
				temp[index] = arr[index1];
				index++;
				index1++;
			}
		}
		
		else {
			while (index2 <= right) {
				temp[index] = arr[index2];
				index++;
				index2++;
			}
		}
		index = 0;
		for (index1 = left; index1 <= right; index1++) {
			arr[index1] = temp[index];
			index++;
		}
	}

	public static void quickSort(int[] list, int N) {
		quickSort(list, 0, N - 1);
	}

	private static void quickSort(int[] list, int first, int last) {
		if (first < last) {
			setPivotToEnd(list, first, last);
			int pivotIndex = splitList(list, first, last);
			quickSort(list, first, pivotIndex - 1);
			quickSort(list, pivotIndex + 1, last);
		}
	}

	private static void setPivotToEnd(int[] arr, int left, int right) {
		int temp;
		int first = left;
		int last = right;
		int center = (left + right) / 2;
		if (arr[first] > arr[center]) {
			temp = arr[first];
			arr[first] = arr[center];
			arr[center] = temp;
		}

		if (arr[last] > arr[first]) {
			temp = arr[first];
			arr[first] = arr[last];
			arr[last] = temp;
		}

		if (arr[last] > arr[center]) {
			temp = arr[last];
			arr[last] = arr[center];
			arr[center] = temp;
		}
	}

	private static int splitList(int[] arr, int left, int right) {
		int temp;
		int indexL = left;
		int indexR = right-1;
		int pivot = right;
		while (indexL <= indexR) {
			while (arr[indexL] < arr[pivot]) {
				indexL++;
			}
			while (arr[indexR] > arr[pivot] && indexL <= indexR) {
				indexR--;
			}
			if (indexL <= indexR) {
				temp = arr[indexL];
				arr[indexL] = arr[indexR];
				arr[indexR] = temp;
				indexL++;
				indexR--;
			}
		}
		temp = arr[indexL];
		arr[indexL] = arr[pivot];
		arr[pivot] = temp;
		return indexL; 
	}
}
