package date_04_07_2025;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FindMostRepeatedElementInArray {
	public static void main(String[] args) {
		int[] arr = {1, 2, 3, 4, 56, 7, 8, 1, 2, 1, 8, 9, 3, 2};

		Map<Integer, Integer> countMap = new LinkedHashMap<>();

		for (int num : arr) {
			countMap.put(num, countMap.getOrDefault(num, 0) + 1);
		}

		System.out.println(countMap);

		for (Entry<Integer, Integer> entry : countMap.entrySet()) {
			if (entry.getValue() > 2) {
				System.out.print(entry.getKey() + " ");
			}
		}
		System.out.println();
		// using java 8

		Arrays.stream(arr).boxed()
				.collect(Collectors.groupingBy(Function.identity(),
						Collectors.counting()))
				.entrySet().stream()
				.max(Comparator.comparingLong(Map.Entry::getValue))
				.map(Entry::getKey).ifPresent(result -> System.out
						.println("Most repeated character is: " + result));
	}

}
