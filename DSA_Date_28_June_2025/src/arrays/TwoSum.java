package arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Problem: Find indexes of two numbers in an array that add up to a target.
 * Approach: Use a HashMap to store (value → index) pairs while iterating.
 */

public class TwoSum {
	public static void main(String[] args) {
		int[] arr = {1, 2, 3, 4, 5, 6};
		int target = 10; // Expected output: [3, 5] → 4 + 6 = 10

		System.out.println(Arrays.toString(twoSum(arr, target)));
	}

	private static int[] twoSum(int[] arr, int target) {
		Map<Integer, Integer> map = new HashMap<>();

		for (int i = 0; i < arr.length; i++) {
			int complement = target - arr[i];

			// Check if the complement exists in the map
			if (map.containsKey(complement)) {
				// Found the two indices: current index and complement's index
				return new int[]{map.get(complement), i};
			}

			// Store the current number and its index in the map
			map.put(arr[i], i);

			// Example progression:
			// i = 0 → arr[i] = 1, complement = 9, map = {1=0}
			// i = 1 → arr[i] = 2, complement = 8, map = {1=0, 2=1}
			// i = 2 → arr[i] = 3, complement = 7, map = {1=0, 2=1, 3=2}
			// i = 3 → arr[i] = 4, complement = 6, map = {1=0, 2=1, 3=2, 4=3}
			// i = 4 → arr[i] = 5, complement = 5, map = {1=0, ..., 5=4}
			// i = 5 → arr[i] = 6, complement = 4 → map contains 4 at index 3 →
			// return [3, 5]
		}
		return new int[]{}; // No valid pair found
	}
}
