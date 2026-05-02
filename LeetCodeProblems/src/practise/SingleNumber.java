package practise;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 
 * Given a non-empty array of integers nums, every element appears twice except
 * for one. Find that single one.
 * 
 * You must implement a solution with a linear runtime complexity and use only
 * constant extra space.
 */
public class SingleNumber {
	public static void main(String[] args) {
		int[] number = {2, 2, 1, 1, 3, 4, 5, 3, 5};
		int singleNumber = singleNumber(number);
		System.out.println(singleNumber);
	}

	// approach: 1
	/*
	 * private static int singleNumber(int[] arr) { int result = 0; for (int num
	 * : arr) { result ^= num; } return result; }
	 */

	// approach: 2
	/*
	 * private static int singleNumber(int[] arr) { Map<Integer, Integer> map =
	 * new LinkedHashMap<>();
	 * 
	 * for (int count : arr) { map.put(count, map.getOrDefault(count, 0) + 1); }
	 * for (int key : map.keySet()) { if (map.get(key) == 1) { return key; } }
	 * return -1; }
	 */

	// approach: 3 using stream api
	private static int singleNumber(int[] arr) {
		return Arrays.stream(arr).reduce(0, (a, b) -> a ^ b);
	}
}
