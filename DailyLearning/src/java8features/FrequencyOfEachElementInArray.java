package java8features;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FrequencyOfEachElementInArray {
	public static void main(String[] args) {
		int[] arr = { 1, 2, 3, 4, 5, 6, 2, 4, 1, 5, 7, 8 };

		// approach: 1
		Map<Integer, Integer> frequencyCount = new LinkedHashMap<>();

		for (int count : arr) {
			frequencyCount.put(count, frequencyCount.getOrDefault(count, 0) + 1);
		}
		System.out.println(frequencyCount);

		// approach: 2
		LinkedHashMap<Integer, Long> collect = Arrays.stream(arr).boxed()
				.collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));
		System.out.println(collect);
	}

}
