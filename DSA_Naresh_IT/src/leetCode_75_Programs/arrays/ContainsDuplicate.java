package leetCode_75_Programs.arrays;

import java.util.HashSet;
import java.util.Set;

public class ContainsDuplicate {
	public static void main(String[] args) {
		int[] arr = { 1, 2, 3, 1 };
		
		System.out.println(containsDuplicate(arr));
	}

	static boolean containsDuplicate(int[] arr) {
		Set<Integer> set = new HashSet<>();

		for (int seen : arr) {
			if (!set.add(seen)) {
				return true;
			}
		}
		return false;
	}

}
