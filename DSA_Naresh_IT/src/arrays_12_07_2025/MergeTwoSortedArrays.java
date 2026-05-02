package arrays_12_07_2025;

import java.util.Arrays;

/**
 * You are given two integer arrays nums1 and nums2, sorted in non-decreasing
 * order, and two integers m and n, representing the number of elements in nums1
 * and nums2 respectively.
 * 
 * https://leetcode.com/problems/merge-sorted-array/description/
 */

public class MergeTwoSortedArrays {

	public static void main(String[] args) {
		int[] arr1 = { 1, 3, 5, 7 };
		int[] arr2 = { 2, 4, 6, 8 };
		int[] arr3 = new int[arr1.length + arr2.length];
//		mergeArrays(arr1, arr2, arr3);
//
//		System.out.println(Arrays.toString(arr3));

//		mergeUsingTwoPointers(arr1, arr2, arr3);
//		System.out.println(Arrays.toString(arr3));

		int[] arr4 = { 1, 2, 3, 0, 0, 0 };
		int[] arr5 = { 4, 5, 6 };
		mergeUsingPointers(arr4, 3, arr5, 3);
		System.out.println(Arrays.toString(arr4));
	}

	static void mergeArrays(int[] a, int[] b, int[] c) {
		int i = 0, j = 0, k = 0;
		int n1 = a.length;
		int n2 = b.length;

		while (i < n1) {
			c[k++] = a[i++];
		}
		while (j < n2) {
			c[k++] = b[j++];
		}

		Arrays.sort(c);
	}

	// Approach : 2 Using Merge of merge sort - Two pointers
	// TC: O(n1) + O(n2) SC: O(n1+n2)
	static void mergeUsingTwoPointers(int[] arr1, int[] arr2, int[] arr3) {
		int n1 = arr1.length;
		int n2 = arr2.length;
		int i = 0, j = 0, k = 0;

		while (i < n1 && j < n2) {
			if (arr1[i] < arr2[j]) {
				arr3[k++] = arr1[i++];
			} else {
				arr3[k++] = arr2[j++];
			}
		}

		// If there are remaining elements of the first array , move them
		while (i < n1) {
			arr3[k++] = arr1[i++];
		}
		// Else if there are any remaining elements in the 2nd array move them
		while (j < n2) {
			arr3[k++] = arr2[j++];
		}

	}

	// Approach 3: Without extra space - Two Pointers (Backward)
	static void mergeUsingPointers(int[] a, int m, int[] b, int n) {
		int i = m - 1;
		int j = n - 1;
		int k = m + n - 1;

		while (i >= 0 && j >= 0) {
			if (a[i] > b[j]) {
				a[k] = a[i];
				i--;
			} else {
				a[k] = b[j];
				j--;
			}
			k--;
		}

		while (j >= 0) {
			a[k] = b[j];
			j--;
			k--;
		}

	}

}
