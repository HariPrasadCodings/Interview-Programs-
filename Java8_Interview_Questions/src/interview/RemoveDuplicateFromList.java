package interview;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RemoveDuplicateFromList {
	public static void main(String[] args) {
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 4, 3, 2, 1, 6, 7, 8, 10);

		// approach: 1
		List<Integer> uniqueElements = numbers.stream().distinct().collect(Collectors.toList());

		// approach: 2
		Set<Integer> unique = numbers.stream().collect(Collectors.toSet());
		System.out.println(unique);

		System.out.println(uniqueElements);
	}
}
