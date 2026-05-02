package leetCode_75_Programs.arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {
	public static void main(String[] args) {
		int[] arr = { 1, 2, 3, 4, 5, 6, 7 }; // [3,5]
		int target = 10;

		System.out.println(Arrays.toString(findTwoSum(arr, target)));
	}

	static int[] findTwoSum(int[] arr, int target) {
		Map<Integer, Integer> map = new HashMap<>();

		for (int i = 0; i < arr.length; i++) {
			int compliment = target - arr[i];

			if (map.containsKey(compliment)) {
				return new int[] { map.get(compliment), i };
			}
			map.put(arr[i], i);
		}
		return new int[] {};
	}

}
