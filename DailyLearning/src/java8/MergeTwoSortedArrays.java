package java8;

import java.util.Arrays;

public class MergeTwoSortedArrays {
	public static void main(String[] args) {
		int[] arr1 = { 1, 3, 5, 0, 0, 0 };
		int[] arr2 = { 2, 4, 6 };

		int m = 3;
		int n = 3;

		int[] result = merge(arr1, m, arr2, n);
		System.out.println(Arrays.toString(result));

	}

	private static int[] merge(int[] arr1, int m, int[] arr2, int n) {
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

		return arr1;
	}

}
