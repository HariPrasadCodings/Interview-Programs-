package sorting_22_07_2025;

import java.util.Arrays;

// TC: O(NlogN) : 

public class QuickSort {
	public static void main(String[] args) {
		int[] arr = { 5, 7, 1, 2, 10, 4, 9, 3, 6, 8 };

		int n = arr.length;

		System.out.println("Befor quick sort: " + Arrays.toString(arr));
		System.out.println();

		quickSort(arr, 0, n - 1);

		System.out.println("After quick sort: " + Arrays.toString(arr));
	}

	// Partition logic to place pivot in its correct position
	private static int partition(int[] arr, int low, int high) {
		int pivot = arr[low]; // Choosing the first element as pivot

		int i = low + 1; // Start from element next to pivot
		int j = high;

		while (i <= j) {
			// Move i until we find element greater than pivot
			while (i <= high && arr[i] <= pivot) {
				i++;
			}
			// Move j until we find element less than or equal to pivot
			while (j >= low + 1 && arr[j] > pivot) {
				j--;
			}

			// If i < j, swap elements at i and j
			if (i < j) {
				// swap arr[i] and arr[j]

				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}

		// Swap pivot with element at j to put it in correct sorted position
		int temp = arr[low];
		arr[low] = arr[j];
		arr[j] = temp;

		// Return the final position of pivot
		return j;

	}

	static void quickSort(int[] arr, int low, int high) {
		if (low < high) {
			int pivotIndex = partition(arr, low, high);
			quickSort(arr, low, pivotIndex - 1);
			quickSort(arr, pivotIndex + 1, high);
		}
	}

}
