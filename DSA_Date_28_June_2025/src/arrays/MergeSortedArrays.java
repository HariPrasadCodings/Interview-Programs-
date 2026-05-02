package arrays;

import java.util.Arrays;

/** Problem: Merge two sorted arrays into one sorted array. */
public class MergeSortedArrays {
	public static void main(String[] args) {
		int[] arr1 = {1, 2, 3};
		int[] arr2 = {4, 5, 6};
		System.out.println(Arrays.toString(merge(arr1, arr2)));
	}

	private static int[] merge(int[] arr1, int[] arr2) {

		int i = 0, j = 0, k = 0;

		int[] result = new int[arr1.length + arr2.length];

		while (i < arr1.length && j < arr2.length) {
			if (arr1[i] < arr2[j]) {
				result[k++] = arr1[i++];
			} else {
				result[k++] = arr2[j++];
			}
		}

		while (i < arr1.length) {
			result[k++] = arr1[i++];
		}

		while (j < arr2.length) {
			result[k++] = arr2[j++];
		}

		return result;
	}

}
