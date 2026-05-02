package arrays;

import java.util.Arrays;

public class MergeTwoSortedArrays {
	public static void main(String[] args) {
		int[] arr1 = { 1, 2, 3, 0, 0, 0 };
		int m = 3;
		int[] arr2 = { 2, 5, 6 };
		int n = 3;
		MergeTwoSortedArrays.mergeTwoSortedArrays(arr1, m, arr2, n);
		System.out.println("Merged Array: " + Arrays.toString(arr1));
	}

	private static void mergeTwoSortedArrays(int[] arr1, int m, int[] arr2, int n) {
		int p1 = m - 1;
		int p2 = n - 1;
		int p = m + n - 1;

		while (p1 >= 0 && p2 >= 0) {
			if (arr1[p1] > arr2[p2]) {
				arr1[p] = arr1[p1];
				p1--;
			} else {
				arr1[p] = arr2[p2];
				p2--;
			}
			p--;
		}
		while (p2 > 0) {
			arr1[p] = arr2[p2];
			p2--;
			p--;
		}
	}
}
