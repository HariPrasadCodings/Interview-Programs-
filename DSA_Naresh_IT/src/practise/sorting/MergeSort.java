package practise.sorting;

import java.util.Arrays;

public class MergeSort {
	public static void main(String[] args) {
		 int[] arr = { 5, 4, 3, 2, 1 };

		System.out.println("Before sorting: " + Arrays.toString(arr));

		mergeSort(arr, 0, arr.length - 1);

	}

	private static void merge(int[] arr, int low, int mid, int high) {
		int[] temp = new int[high - low + 1];
		int left = low;
		int right = mid + 1;
		int k = 0;

		System.out.println("Merging from index: " + low + " to " + high);

		while (left <= mid && right <= high) {
			if (arr[left] <= arr[right]) {
				temp[k++] = arr[left++];
			} else {
				temp[k++] = arr[right++];
			}
		}

		while (left <= mid) {
			temp[k++] = arr[left++];
		}
		while (right <= high) {
			temp[k++] = arr[right++];
		}

		for (int i = 0; i < temp.length; i++) {
			arr[low + i] = temp[i];
		}

		System.out.println("After Merging: ");
		for (int i = low; i <= high; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	// Recursive merge sort
	public static void mergeSort(int[] arr, int low, int high) {
		if (low >= high) {
			return;
		}
		int mid = (low + high) / 2;
		mergeSort(arr, low, mid); // sort left half
		mergeSort(arr, mid + 1, high); // sort right half
		merge(arr, low, mid, high); // Merge sorted halves

	}

}
