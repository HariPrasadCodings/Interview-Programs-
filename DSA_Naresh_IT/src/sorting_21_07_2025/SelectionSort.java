package sorting_21_07_2025;

import java.util.Arrays;

public class SelectionSort {
	public static void main(String[] args) {
		int[] arr = { 4, 9, 3, 8, 6, 1, 7 };

		selectionSort(arr);

		System.out.println(Arrays.toString(arr));
	}

	// Function to perform selection sort
	private static void selectionSort(int[] arr) {
		int n = arr.length;

		for (int i = 0; i < n - 1; i++) { // Number of passes to run outer loop
			int minIndex = i;

			for (int j = i + 1; j < n; j++) { // find the index value of minimum element
				if (arr[j] < arr[minIndex]) {
					minIndex = j;
				}
			}

			int temp = arr[minIndex]; // Swap the selected element at minIndex
			arr[minIndex] = arr[i];
			arr[i] = temp;
		}
	}

}
