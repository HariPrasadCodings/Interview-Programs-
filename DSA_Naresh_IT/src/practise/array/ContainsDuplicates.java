package practise.array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Problem: Determine if an array contains any duplicate elements.
 */

/**
 * Explanation: Use a hash set to track seen numbers, returning true if a number
 * is already present. Optimizes from O(n²) or O(n log n) with sorting. Handles
 * empty arrays.
 */
public class ContainsDuplicates {
	public static void main(String[] args) {
		int[] arr = { 1, 2, 3, 4, 5, 4 }; // 2 true
		System.out.println(isContainsDuplicates(arr));
		System.out.println(isContainsDuplicatesUsingMap(arr));
	}

	/**
	 * TC: O(n) - Single pass with O(1) hash set operations. SC: O(n) - Hash set
	 * stores up to n elements.
	 */
	static boolean isContainsDuplicates(int[] arr) {
		Set<Integer> set = new HashSet<>();

		for (int num : arr) {
			if (set.contains(num)) {
				return true;
			}
			set.add(num);
		}
		return false;
	}

	static boolean isContainsDuplicatesUsingMap(int[] nums) {
		Map<Integer, Integer> map = new HashMap<>();

		for (int num : nums) {
			map.put(num, map.getOrDefault(num, 0) + 1);
		}

		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			if (entry.getValue() > 1) {
				return true;
			}
		}
		return false;

	}
}
