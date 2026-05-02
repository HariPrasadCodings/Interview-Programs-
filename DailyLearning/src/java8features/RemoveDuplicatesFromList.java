package java8features;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RemoveDuplicatesFromList {
	public static void main(String[] args) {
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 7, 5, 6, 7, 8, 9, 2, 5);
		Set<Integer> set = new HashSet<>();
		// find duplicates from the list
		Set<Integer> duplicates = numbers.stream().filter(n -> !set.add(n)).collect(Collectors.toSet());
		System.out.println(duplicates);

		// remove duplicates
		List<Integer> nonDuplicates = numbers.stream().distinct().collect(Collectors.toList());
		System.out.println(nonDuplicates);

	}

}
