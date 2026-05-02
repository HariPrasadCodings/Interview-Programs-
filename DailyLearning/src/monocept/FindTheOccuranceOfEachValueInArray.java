package monocept;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FindTheOccuranceOfEachValueInArray {
	public static void main(String[] args) {
		int[] arr = { 2, 3, 4, 5, 7, 2, 5, 4, 1, 9 };

		// traditional approach
		Map<Integer, Integer> count = new LinkedHashMap<>();

		for (int frequency : arr) {
			count.put(frequency, count.getOrDefault(frequency, 0) + 1);
		}
		System.out.println(count);

		// using stream api
		Map<Integer, Long> collect = Arrays.stream(arr).boxed()
				.collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));
		System.out.println(collect);
	}

}
