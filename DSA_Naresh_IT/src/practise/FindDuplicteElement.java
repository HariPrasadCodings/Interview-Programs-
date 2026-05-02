package practise;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class FindDuplicteElement {
	public static void main(String[] args) {
		int[] arr = { 1, 3, 4, 2, 2 };
		System.out.println("Duplicate number using HashMap is: " + findDuplicateUsingHashMap(arr));

		System.out.println("Duplicate number using Set is: " + findDuplicateUsingSet(arr));

		System.out.println("Duplicate number using Sign flipping is: " + findDuplicateUsingSignFlipping(arr));

	}

	// Approach : 1 using HashMap
	static int findDuplicateUsingHashMap(int[] arr) {
		Map<Integer, Integer> map = new HashMap<>();

		for (int num : arr) {
			map.put(num, map.getOrDefault(num, 0) + 1);
		}

		for (Entry<Integer, Integer> entry : map.entrySet()) {
			if (entry.getValue() > 1) {
				return entry.getKey();
			}
		}
		return -1;
	}

	// Approach using set
	static int findDuplicateUsingSet(int[] arr) {
		Set<Integer> set = new HashSet<>();

		for (int num : arr) {
			if (set.contains(num)) {
				return num;
			} else {
				set.add(num);
			}
		}
		return -1;
	}

	// Approach 3: Sign Flipping (In-Place Modification)
	static int findDuplicateUsingSignFlipping(int[] arr) {
		int ans = 0;
		for (int i = 0; i < arr.length; i++) {
			int index = Math.abs(arr[i]);
			if (arr[index] < 0) {
				ans = index;
			}

			arr[index] = -arr[index];
		}
		return ans;
	}
}
