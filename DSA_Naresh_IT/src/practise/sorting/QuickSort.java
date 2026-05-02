package practise.sorting;

import java.util.Arrays;

public class QuickSort {
	public static void main(String[] args) {
		int[] arr = { 5, 7, 6, 4, 3, 2, 1 };

		System.out.println("Before sorting: " + Arrays.toString(arr));

		quickSort(arr, 0, arr.length - 1);

		System.out.println("After sorting: " + Arrays.toString(arr));

	}

	static void quickSort(int[] arr, int i, int j) {
		if (i < j) {
			int pIndex = partition(arr, i, j);

			quickSort(arr, i, pIndex);

			quickSort(arr, pIndex + 1, j);
		}
	}

	static void swap(int[] arr, int a, int b) {
		if (a < b) {
			int temp = arr[a];
			arr[a] = arr[b];
			arr[b] = temp;
		}
	}

	static int partition(int[] arr, int a, int b) {
		int pivot = arr[a + (b - a) / 2];
		int i = a;
		int j = b;

		while (i <= j) {
			while (arr[i] < pivot)
				i++;
			while (arr[j] > pivot)
				j--;

			if (i <= j) {
				swap(arr, i, j);
				i++;
				j--;
			}
		}

		return i - 1;
	}

}
