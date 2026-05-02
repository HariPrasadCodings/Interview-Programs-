package leetCode_75_Programs.arrays;

import java.util.Arrays;

public class MergeSortedArray {
	public static void main(String[] args) {
		int[] arr1 = { 1, 2, 3, 0, 0, 0 };
		int m = 3;

		int[] arr2 = { 4, 5, 6 };
		int n = 3;

		mergeSortedArrays(arr1, m, arr2, n);

		System.out.println(Arrays.toString(arr1));

	}

	static void mergeSortedArrays(int[] arr1, int m, int[] arr2, int n) {
		int i = m - 1; // 2
		int j = n - 1; // 2
		int k = m + n - 1; // 5

		while (i >= 0 && j >= 0) {
			if (arr1[i] > arr2[j]) {
				arr1[k--] = arr1[i--];
			} else {
				arr1[k--] = arr2[j--];
			}
		}

		while (j > 0) {
			arr1[k--] = arr2[j--];
		}

	}

}
