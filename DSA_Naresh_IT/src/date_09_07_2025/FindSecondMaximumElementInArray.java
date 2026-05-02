package date_09_07_2025;

import java.util.Arrays;

public class FindSecondMaximumElementInArray {
	public static void main(String[] args) {
		int[] arr = { 5, 2, 3, 8, 7, 10, 9, 6 };

		System.out.println("Second Maximum element is: " + findSecondMax(arr));

		System.out.println("Second Maximum element is: " + findSecondMaximum(arr));

		System.out.println("Second Maximum element is: " + findSecondMaxUsingOptimalApproach(arr));

	}

	static int findSecondMax(int[] arr) {
		int n = arr.length;
		// 1. Sorting elements in ascending order
		Arrays.sort(arr);

		// returning second max that is n - 2
		int max = arr[arr.length - 1];

		for (int i = n - 1; i >= 0; i--) {
			if (arr[i] < max) {
				return arr[i];
			}
		}
		System.out.println("All elements are equal .");
		return -1;
	}

	// Approach: 2 : TC: O(N) + O(N) = O(2N) SC: O(1)
	static int findSecondMaximum(int[] arr) {
		int n = arr.length;
		int secondMax = Integer.MIN_VALUE;
		int max = arr[0];
		for (int i = 0; i < n; i++) {
			if (arr[i] > max) {
				max = arr[i];
			}
		}
		for (int i = 0; i < n; i++) {
			if (arr[i] > secondMax && arr[i] != max) {
				secondMax = arr[i];
			}
		}
		return secondMax;
	}

	// Approach : 3 Optimal Approach
	// TC: O(N) SC: O(1)
	static int findSecondMaxUsingOptimalApproach(int[] arr) {
		int max = arr[0];
		int secondMax = -1;

		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > max) {
				secondMax = max;
				max = arr[i];
			} else if (arr[i] > secondMax && arr[i] < max) {
				secondMax = arr[i];
			}
		}
		return secondMax;
	}

}
