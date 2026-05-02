package arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FindIndexOfTargetValue {
	public static void main(String[] args) {
		int[] arr = { 2, 11, 8, 10, 7 };
		int target = 9;
		int[] result = findTwoSum(arr, target);
		System.out.println(Arrays.toString(result));
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
		return arr;
	}

}
