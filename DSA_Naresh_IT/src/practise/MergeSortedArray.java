package practise;

import java.util.Arrays;

public class MergeSortedArray {
	public static void main(String[] args) {
		int[] arr1 = { 1, 3, 5, 0, 0, 0 };
		int m = 3;
		int[] arr2 = { 7, 8, 9 };
		int n = 3;

		merge(arr1, m, arr2, n);

		System.out.println("After Merging: " + Arrays.toString(arr1));
	}

	public static void merge(int[] arr1, int m, int[] arr2, int n) {
		int i = m - 1;
		int j = n - 1;
		int k = m + n - 1;

		while (i >= 0 && j >= 0) {
			if (arr1[i] > arr2[j]) {
				arr1[k--] = arr1[i--];
			} else {
				arr1[k--] = arr2[j--];
			}
		}

		while (j >= 0) {
			arr1[k--] = arr2[j--];
		}
	}

}
