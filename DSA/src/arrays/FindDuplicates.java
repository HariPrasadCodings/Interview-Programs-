package arrays;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class FindDuplicates {
	public static void main(String[] args) {
		int[] arr = { 1, 3, 4, 5, 6, 1, 2, 3, 4, 9, 0 }; // 1,3,4

		System.out.println("Duplicate elements: " + Arrays.toString(findDuplicates(arr)));
	}

	static int[] findDuplicates(int[] arr) {
		Set<Integer> seen = new TreeSet<>();
		Set<Integer> duplicates = new TreeSet<>();

		for (int num : arr) {
			if (!seen.add(num)) {
				duplicates.add(num);
			}
		}

		int[] result = new int[duplicates.size()];

		int i = 0;

		for (int num : duplicates) {
			result[i++] = num;
		}
		return result;
	}

}
