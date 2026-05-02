package date_10_07_2025;

import java.util.HashMap;
import java.util.Map;

/**
 * 136. Single Number : LEETCODE
 * 
 * Given a non-empty array of integers nums, every element appears twice except
 * for one. Find that single one.
 * 
 * You must implement a solution with a linear runtime complexity and use only
 * constant extra space.
 */

public class SingleNumber {
	public static void main(String[] args) {

		int[] arr = { 3, 2, 3, 4, 2, 1, 1 };
		System.out.println("Single Number is: " + findSingleNumberUsingBruteForce(arr));

		int[] arr1 = { 3, 4, 9, 3, 7, 4, 8, 9, 8 };
		System.out.println("Single Number is: " + findSingleNumberUsingHashMap(arr1));

		int[] arr2 = { 2, 1, 2, 4, 1 };
		System.out.println("Single Number is: " + findSingleNumberUsingXOR(arr2));

	}

	// Approach 2: HashMap (Counting) TC: O(N) SC: O(N)
	private static int findSingleNumberUsingHashMap(int[] arr) {
		Map<Integer, Integer> map = new HashMap<>();

		for (int num : arr) {
			map.put(num, map.getOrDefault(num, 0) + 1);
		}

		for (int entry : map.keySet()) {
			if (map.get(entry) == 1) {
				return entry;
			}
		}
		return -1;
	}

	// Approach : 1 Brute - force approach TC: O(N^2) SC : O(1)
	// Loop through each element and check
	private static int findSingleNumberUsingBruteForce(int[] arr) {

		for (int i = 0; i < arr.length; i++) {
			int count = 0;
			for (int j = 0; j < arr.length; j++) {
				if (arr[i] == arr[j]) {
					count++;
				}
			}
			// If it appear only once, return it.
			if (count == 1) {
				return arr[i];
			}
		}
		return -1;
	}

	// Approach : 3 Optimal Approach XOR
	// TC: O(N) ; SC : O(1)
	private static int findSingleNumberUsingXOR(int[] arr) {
		int result = arr[0];
		for (int i = 1; i < arr.length; i++) {
			result = result ^ arr[i];
		}
		return result;
	}

}
