package sorting_21_07_2025;

import java.util.Arrays;

public class BubbleSorting {
	public static void main(String[] args) {
		int[] arr = { 50, 40, 10, 30, 20 };

		bubbleSort(arr);

		System.out.println(Arrays.toString(arr));

		int[] arr1 = { 1, 2, 3, 4, 5 };

		bubbleSortOptimal(arr1);
		System.out.println(Arrays.toString(arr1));
	}

	// worst case : O(n^2)
	private static void bubbleSort(int[] arr) {
		int n = arr.length;

		for (int i = 0; i < n - 1; i++) { // Number of passes
			for (int j = 0; j < n - i - 1; j++) { // Number of comparisons
				if (arr[j] > arr[j + 1]) {
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}
	}

	// Best case: O(n)
	private static void bubbleSortOptimal(int[] arr) {
		int n = arr.length;

		for (int i = 0; i < n - 1; i++) {
			System.out.println(i + " ");
			boolean swapped = false;
			for (int j = 0; j < n - i - 1; j++) {
				if (arr[j] > arr[j + 1]) {
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
					swapped = true;
				}
			}
			if (!swapped) {
				break;
			}
		}
	}

}
