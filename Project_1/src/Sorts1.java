/* Jackson McDaniel - jkmcdani@calpoly.edu
Thomas Bramble - trbrambl@calpoly.edu
Project 1 - 10/05/2018 */

public class Sorts1 {

    private static long selectionCount;
    private static long mergeCount;
    private static long quickCount;
	
	public static long selectionSort(int[] list, int N) {
        selectionCount = 0;
		int minIndex = 0;
		int temp;
		for (int i = 0; i < N; i++) {
			temp = list[i];
			for (int j = i+1; j < N; j++) {
                selectionCount++;
				if (list[j] < temp) {
					minIndex = j;
					temp = list[j];
				}
			}
			arr[minIndex] = list[i];
			list[i] = temp;
        }
        return selectionCount;
	}
	
	public static long mergeSort(int[] list, int N) {
        mergeCount = 0;
        mergeSort(list, 0, N-1);
        return mergeCount;
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
            mergeCount++;
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

	public static long quickSort(int[] list, int N) {
        quickCount = 0;
        quickSort(list, 0, N - 1);
        return quickCount;
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
        quickCount += 3;
		if (arr[first] > arr[center]) {
			temp = arr[first];
			arr[first] = arr[center];
			arr[center] = temp;
		}

		if (arr[last] < arr[first]) {
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
		int indexR = right - 1;
		int pivot = right;
		while (indexL <= indexR) {
			while (arr[indexL] < arr[pivot]) {
                indexL++;
                quickCount++;
			}
			quickCount++;
			while (indexL <= indexR && arr[indexR] > arr[pivot]) {
                indexR--;
                quickCount++;
			}
			if (indexL <= indexR) {
				temp = arr[indexL];
				arr[indexL] = arr[indexR];
				arr[indexR] = temp;
				indexL++;
				indexR--;
				quickCount++;
			}
		}
		temp = arr[indexL];
		arr[indexL] = arr[pivot];
		arr[pivot] = temp;
		return indexL; 
	}
}
