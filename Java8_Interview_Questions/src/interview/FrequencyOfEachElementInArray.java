package interview;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FrequencyOfEachElementInArray {
	public static void main(String[] args) {
		String[] arr = {"hari", "ravi", "kiran", "ramesh", "hari"};
		// approach: 1
		Map<String, Long> collect = Arrays.stream(arr).collect(Collectors
				.groupingBy(Function.identity(), Collectors.counting()));

		// approach: 2
		Map<String, Integer> eachValueCount = new HashMap<>();

		for (String ch : arr) {
			eachValueCount.put(ch, eachValueCount.getOrDefault(ch, 0) + 1);
		}

		System.out.println(eachValueCount);

		System.out.println(collect);
	}

}
