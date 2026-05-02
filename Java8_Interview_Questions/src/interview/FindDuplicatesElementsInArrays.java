package interview;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class FindDuplicatesElementsInArrays {
	public static void main(String[] args) {
		Set<Integer> set = new HashSet<>();

		// using java 8 streams

		int[] arr = {1, 2, 3, 4, 5, 6, 3, 2, 1};

		Arrays.stream(arr).filter(n -> !set.add(n)).boxed().toList()
				.forEach(System.out::println);

		// Approach : 2 Traditional approach

		Set<Integer> duplicates = new HashSet<>();

		Set<Integer> seen = new HashSet<>();

		for (int num : arr) {
			if (!seen.add(num)) {
				duplicates.add(num);
			}
		}
		System.out.println("Duplicates: " + duplicates);

	}

}
