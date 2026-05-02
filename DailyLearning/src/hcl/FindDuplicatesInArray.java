package hcl;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class FindDuplicatesInArray {
	public static void main(String[] args) {
		int[] arr = { 1, 2, 3, 4, 2, 5, 4 };
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 2, 5, 4);
		Set<Integer> set = new HashSet<>();

		// approach:1
		List<Integer> collect = numbers.stream().filter(i -> !set.add(i)).collect(Collectors.toList());
		System.out.println(collect);

		Map<Integer, Integer> duplicates = new HashMap<>();

		for (int count : arr) {
			duplicates.put(count, duplicates.getOrDefault(count, 0) + 1);
		}

		// approach:2
		Set<Integer> duplicateIntegers = duplicates.entrySet().stream().filter(x -> x.getValue() > 1)
				.map(Map.Entry::getKey).collect(Collectors.toSet());
		System.out.println(duplicateIntegers);

		// approach:3
		List<Integer> listDuplicates = numbers.stream().filter(num -> Collections.frequency(numbers, num) > 1)
				.distinct().collect(Collectors.toList());
		System.out.println(listDuplicates);
	}

}
