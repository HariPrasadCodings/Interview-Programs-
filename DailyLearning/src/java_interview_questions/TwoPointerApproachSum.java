package java_interview_questions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoPointerApproachSum {
	public static void main(String[] args) {
		int[] arr = { 1, 2, 3, 4, 5, 6 };
		int target = 4;
		System.out.println(Arrays.toString(twoPointer(arr, target)));
	}

	private static int[] twoPointer(int[] arr, int target) {

		Map<Integer, Integer> newMap = new HashMap<>();

		for (int i = 0; i < arr.length; i++) {
			int compliment = target - arr[i];
			if (newMap.containsKey(compliment)) {
				return new int[] { newMap.get(compliment), i };
			}
			newMap.put(arr[i], i);
		}

		return new int[] {};

	}

}
