package practise.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Problem: Given an array of integers nums and an integer target, return
 * indices of two numbers such that they add up to target.
 */

/**
 * Explanation: We need to find two numbers whose sum equals the target. A hash
 * map stores numbers and their indices, allowing us to find the complement
 * (target - nums[i]) in O(1) time, reducing the time complexity from O(n²) to
 * O(n). Edge cases include an empty array or no solution.
 */

/**
 * Time Complexity (TC): O(n) - Single pass through the array with O(1) hash map
 * operations. Space Complexity (SC): O(n) - Hash map stores up to n elements.
 */

public class TwoSum {
	public static void main(String[] args) {
		int[] arr = { 2, 5, 7, 8, 9, 10 };
		int target = 15;

		int[] twoSum = findTwoSum(arr, target);
		System.out.println("Two sum is: " + Arrays.toString(twoSum));

	}

	static int[] findTwoSum(int[] arr, int target) {
		Map<Integer, Integer> map = new HashMap<>();
		int n = arr.length;

		for (int i = 0; i < n; i++) {
			int compliment = target - arr[i]; // 15 - 2 = 13
			if (map.containsKey(compliment)) {
				return new int[] { map.get(compliment), i };
			}
			map.put(arr[i], i);
		}
		return new int[] {};
	}

}
