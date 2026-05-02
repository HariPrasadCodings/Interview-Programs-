package maximum;

import java.util.Arrays;

public class FindMaximumElement {
	public static void main(String[] args) {
		int[] arr = { 1, 8, 7, 56, 90 };

		System.out.println("Maximum Element using linear scan is: " + findMaxUsingLinearScan(arr));

		System.out.println("Maximum Element Sorting-Based Approach is: " + findMaxUsingSortingApproach(arr));

	}

	// Approach: 1. Linear Scan - Brute Force
	// Time Complexity: O(n)
	// Space Complexity: O(1)
	private static int findMaxUsingLinearScan(int[] arr) {
		// 1. Initialize max as first element.
		int max = arr[0];

		// 2. Loop from index 1 to end.
		for (int i = 0; i < arr.length; i++) {
			// 3. If arr[i] > max, update max.
			if (arr[i] > max) {
				max = arr[i];
			}
		}

		// 4. Return max.
		return max;
	}

	// Approach 2. Sorting-Based Approach
	/*
	 * Time Complexity: O(n log n) Space Complexity: O(1) or O(n) (depends on
	 * sorting algorithm) Not efficient just to find maximum
	 */
	private static int findMaxUsingSortingApproach(int[] arr) {
		// 1. Sort the array.
		Arrays.sort(arr);
		return arr[arr.length - 1];
	}

}
