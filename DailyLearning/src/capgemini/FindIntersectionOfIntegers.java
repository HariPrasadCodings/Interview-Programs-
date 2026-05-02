package capgemini;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FindIntersectionOfIntegers {
	public static void main(String[] args) {
		Integer[] arr1 = {1, 2, 2, 1};
		Integer[] arr2 = {2, 2};

		// Intersection common elements between two arrays
		Set<Integer> set1 = Arrays.stream(arr1).collect(Collectors.toSet());

		List<Integer> result = Arrays.stream(arr2).filter(set1::contains)
				.distinct().toList();
		System.out.println(result);

		// approach:2
		List<Integer> list = Arrays.stream(arr1)
				.filter(x -> Arrays.stream(arr2).anyMatch(y -> y == x))
				.distinct().toList();
		System.out.println(list);
	}

}
