package date_10_07_2025;

/**
 * 
 * LEET CODE: 268. Missing Number
 * 
 * Given an array nums containing n distinct numbers in the range [0, n], return
 * the only number in the range that is missing from the array.
 */

public class MissingNumber {
	public static void main(String[] args) {
		int[] arr = { 1, 2, 3, 5, 6 };

		System.out.println("Missing Number: " + missingNumber(arr));

		System.out.println("Missing Number: " + missingNumberUsingXOR(arr));
	}

	// Approach : 2 TC:O(N) SC:O(1)
	static int missingNumber(int[] arr) {
		int n = arr.length + 1;
		int totalSum = n * (n + 1) / 2;
		int originalSum = 0;

		for (int i = 0; i < arr.length; i++) {
			originalSum = originalSum + arr[i];
		}

		return totalSum - originalSum;
	}

	// TC: SC:
	static int missingNumberUsingXOR(int[] arr) {
		int result = 0;
		int n = arr.length;

		// XOR all numbers from 0 to n
		for (int i = 0; i <= n; i++) {
			result ^= i;

		}

		// XOR all elements in the array
		for (int num : arr) {
			result ^= num;
		}

		return result;
	}

}
