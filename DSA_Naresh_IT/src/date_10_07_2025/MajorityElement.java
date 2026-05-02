package date_10_07_2025;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MajorityElement {
	public static void main(String[] args) {
		int[] arr = { 2, 2, 2, 3, 4, 2, 5, 2, 2, 6 };

		System.out.println("Majority element: " + majoryUsingBruteForce(arr));

		System.out.println("Majority element: " + majorityUsingSorting(arr));

		System.out.println("Majority element: " + majorityElementUsingHashmap(arr));

		System.out.println("Majority element: " + majorityElementUsingBuyerMore(arr));

	}

	// Approach 1: Brute-force TC: O(N^2) SC: O(1)

	static int majoryUsingBruteForce(int[] arr) {
		int n = arr.length;
		int x = n / 2;

		for (int i = 0; i < n; i++) {
			int count = 0;
			for (int j = 0; j < n; j++) {
				if (arr[i] == arr[j]) {
					count++;
				}
			}
			if (count > x) {
				return arr[i];
			}
		}
		return -1;
	}

	// Approach : 2
	static int majorityUsingSorting(int[] arr) {
		Arrays.sort(arr);

		return arr[arr.length / 2];
	}

	// Approach : 3
	static int majorityElementUsingHashmap(int[] arr) {
		Map<Integer, Integer> map = new HashMap<>();
		int n = arr.length;
		int thresHold = n / 2;

		for (int num : arr) {
			map.put(num, map.getOrDefault(num, 0) + 1);
		}

		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			if (entry.getValue() > thresHold) {
				return entry.getKey();
			}
		}
		return -1;
	}

	static int majorityElementUsingBuyerMore(int[] arr) {
		int majority = arr[0];
		int vote = 1;

		for (int i = 1; i < arr.length; i++) {

			if (vote == 0) {
				majority = arr[i];
				vote = 1;
			} else if (arr[i] == majority) {
				vote++;
			} else {
				vote--;
			}

		}

		// phase 2: verify candidate
		int count = 0;
		for (int num : arr) {
			if (num == majority) {
				count++;
			}
		}
		if (count > arr.length / 2) {
			return majority;
		} else {
			return -1;
		}
	}

}
