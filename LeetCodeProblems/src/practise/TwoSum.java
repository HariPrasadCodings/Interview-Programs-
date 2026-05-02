package practise;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of Integers and an target, return indices of the two numbers
 * such that they add upto target.
 * 
 * @author HariPrasad
 * 
 */
public class TwoSum {
	public static void main(String[] args) {
		int[] arr = {2, 1, 11, 15, 7};
		int target = 9;
		int[] result = findTwoSum(arr, target);
		System.out.println(Arrays.toString(result));
	}

	private static int[] findTwoSum(int[] arr, int target) {
		Map<Integer, Integer> map = new HashMap<>();

		for (int i = 0; i < arr.length; i++) {
			// This calculates the difference between the target and the current
			// element
			// (arr[i]). This is the value needed to reach the target.
			int compliment = target - arr[i];
			// The program checks if the complement exists in the map. If it
			// does, this means a pair of numbers has been found that sums to
			// the target.
			if (map.containsKey(compliment)) {
				// If the complement is found, the indices of the two numbers
				// are returned as an
				// array.
				return new int[]{map.get(compliment), i};
			}
			map.put(arr[i], i);
		}
		throw new IllegalArgumentException(
				"No elements found with the indeces");
	}

}
