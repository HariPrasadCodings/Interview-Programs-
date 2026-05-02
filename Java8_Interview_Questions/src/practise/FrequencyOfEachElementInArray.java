package practise;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FrequencyOfEachElementInArray {
	public static void main(String[] args) {
		int[] nums = { 1, 2, 3, 4, 5, 3, 2, 1, 4, 5, 6, 7, 8, 9 };

		Map<Integer, Integer> count = new LinkedHashMap<>();

		for (int c : nums) {
			count.put(c, count.getOrDefault(c, 0) + 1);
		}
		System.out.println(count);

		// using java 8
		Map<Integer, Long> frequency = Arrays.stream(nums).boxed()
				.collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));
		System.out.println(frequency);

		// approach : 3
		Map<Integer, Integer> freq = new LinkedHashMap<>();
		IntStream.of(nums).forEach(n -> freq.put(n, freq.getOrDefault(n, 0) + 1));
		System.out.println(freq);
	}

}
