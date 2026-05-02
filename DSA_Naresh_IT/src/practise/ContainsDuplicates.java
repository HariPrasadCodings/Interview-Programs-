package practise;

import java.util.HashSet;
import java.util.Set;

public class ContainsDuplicates {
	public static void main(String[] args) {
		int[] arr = { 1, 3, 5, 6, 7, 3, 2 };

		System.out.println(containsDuplicate(arr));
	}

	static boolean containsDuplicate(int[] arr) {
		Set<Integer> set = new HashSet<>();

		for (int num : arr) {
			if (!set.add(num)) {
				return true;
			}
		}
		return false;
	}
}
