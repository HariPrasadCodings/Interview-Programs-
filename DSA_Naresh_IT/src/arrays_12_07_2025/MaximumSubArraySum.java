package arrays_12_07_2025;

/**
 * https://leetcode.com/problems/maximum-subarray/description/
 */
public class MaximumSubArraySum {
	public static void main(String[] args) {
		int[] arr = { 1, -2, 3, -1, 2 };

		System.out.println("Maximum sum of sub array is: " + findMaxSubArraySumBruteForce(arr));

		System.out.println("Maximum sum of sub array is: " + findMaxSumUsingKadanesAlgorithm(arr));
	}

	// TC: O(N^2) SC: O(1)
	static int findMaxSubArraySumBruteForce(int[] arr) {
		int n = arr.length;
		int maxSum = Integer.MIN_VALUE;

		for (int i = 0; i < n; i++) {
			int currentSum = 0;
			for (int j = i; j < n; j++) {
				currentSum = currentSum + arr[j];
				maxSum = Math.max(currentSum, maxSum);
			}
		}
		return maxSum;
	}

	// TC: O(N) SC: O(1)
	static int findMaxSumUsingKadanesAlgorithm(int[] arr) {
		int result = arr[0];
		
		int maxEnding = arr[0];

		for (int i = 1; i < arr.length; i++) {

			maxEnding = Math.max(maxEnding + arr[i], arr[i]);
			result = Math.max(maxEnding, result);
		}
		return result;
	}

}
