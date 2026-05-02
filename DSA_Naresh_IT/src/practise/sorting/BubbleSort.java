package practise.sorting;

import java.util.Arrays;

public class BubbleSort {
	public static void main(String[] args) {
		int[] arr = { 5, 4, 3, 2, 6, 1 };

		System.out.println("Before sorting: " + Arrays.toString(arr));
		System.out.println();
		bubbleSort(arr);
		System.out.println();
		System.out.println("After sorting: " + Arrays.toString(arr));
	}

	// Time Complexity: O(n)
	static void bubbleSort(int[] arr) {
		int n = arr.length;
		boolean swapped;

		for (int i = 0; i < n - 1; i++) {
			System.out.print(i + " ");
			swapped = false;
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
