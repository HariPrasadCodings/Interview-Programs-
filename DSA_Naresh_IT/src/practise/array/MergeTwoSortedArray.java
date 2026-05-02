package practise.array;

import java.util.Arrays;

/**Problem: (LeetCode 88)
 * Merge two sorted arrays nums1 and nums2 into nums1 as one sorted
    array.
*/

/**
 * Explanation: Merge from the end to avoid overwriting elements in nums1, com-
 * paring the largest elements and placing them at the end. Handles cases where
 * one array is empty.
 */

/**
 * TC: O(m + n) - Process each element once. SC: O(1) - In-place merging.
 */

public class MergeTwoSortedArray {
	public static void main(String[] args) {
		int[] arr1 = { 1, 2, 3 };
		int[] arr2 = { 4, 5, 6, 7 };

		merge(arr1, arr2); // 1,2,3,4,5,6,7

	}

	static void merge(int[] arr1, int[] arr2) {
		int[] result = new int[arr1.length + arr2.length];
		int i = 0;
		int j = 0;
		int m = arr1.length;
		int n = arr2.length;
		int k = 0;

		while (i < m && j < n) {
			if (arr1[i] <= arr2[j]) {
				result[k++] = arr1[i++];
			} else {
				result[k++] = arr2[j++];
			}
		}
		while (i < m) {
			result[k++] = arr1[i++];
		}

		while (j < n) {
			result[k++] = arr2[j++];
		}

		System.out.println("After Merge: " + Arrays.toString(result));
	}

}
