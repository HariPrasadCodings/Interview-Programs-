package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindDuplicateInArray {
	public static void main(String[] args) {
		int[] a1 = { 2, 3, 1, 2, 3 };
		int[] a2 = { 0, 3, 1, 2 };
		int[] a3 = { 2 };
		System.out.println(findDuplicates(a1));
		System.out.println(findDuplicates(a2));
		System.out.println(findDuplicates(a3));
		System.out.println(removeDuplicates(a1));
	}

	private static List<Integer> findDuplicates(int[] arr) {
		Set<Integer> seen = new HashSet<>();
		return Arrays.stream(arr).filter(n -> !seen.add(n)).distinct().boxed().toList();
	}

	private static List<Integer> removeDuplicates(int[] arr) {

		return Arrays.stream(arr).boxed().distinct().toList();
	}

}
