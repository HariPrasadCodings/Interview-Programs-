package arrays;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//Problem number is : 217

public class ContainsDuplicates {
	public static void main(String[] args) {
		int[] arr = {1, 2, 3, 1};
		System.out.println(hasDuplicatesUsingLoop(arr));
		System.out.println(hasDuplicatesUsingStream(arr));
	}

	private static boolean hasDuplicatesUsingLoop(int[] arr) {
		int n = arr.length;
		Set<Integer> set = new HashSet<>();
		// for (int i = 0; i < n; i++) {
		// if (!set.add(arr[i])) {
		// return true;
		// }
		// }
		for (int result : arr) {
			if (!set.add(result)) {
				return true;
			}
		}
		return false;
	}

	// using java8 stream api
	private static boolean hasDuplicatesUsingStream(int[] arr) {
		Set<Integer> seen = new HashSet<>();
		return Arrays.stream(arr).anyMatch(n -> !seen.add(n));
	}

}
