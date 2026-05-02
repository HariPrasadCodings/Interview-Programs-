package arrays_11_07_2025;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {
	public static void main(String[] args) {
		int[] arr = { 2, 7, 11, 15 };
		int target = 9;

		System.out.println(Arrays.toString(twoSum(arr, target)));
	}

	static int[] twoSum(int[] a, int target) {

		Map<Integer, Integer> map = new HashMap<>();

		for (int i = 0; i < a.length; i++) {
			int compliment = target - a[i];
			if (map.containsKey(compliment)) {
				return new int[] { map.get(compliment), i };
			}

			map.put(a[i], i);
		}

		return new int[] {};

	}

	// Approach : 2 Brute Force
	static int[] twoSumUsingBruteForce(int[] a, int target) {

		int n = a.length;

		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				if (a[i] == a[j]) {
					return new int[] { a[i] + a[j] };
				}
			}
		}
		return new int[] {};
	}

}
