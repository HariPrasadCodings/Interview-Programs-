package practise;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FindTwoSum {
	public static void main(String[] args) {
		int[] arr = { 6, 4, 5, 2, 1, 9, 3 };
		int target = 6;

		System.out.println(Arrays.toString(findTwoSum(arr, target)));
	}

	public static int[] findTwoSum(int[] arr, int target) {
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
