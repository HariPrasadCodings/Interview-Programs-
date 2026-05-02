package monocept;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FindTheRepeatedPatternInString {
	public static void main(String[] args) {
		String[] patterns = { "ab,abc,abc,ab,abz,abc" };

		// Traditional approach
		Map<String, Integer> count = new LinkedHashMap<>();

		for (String ch : patterns) {
			String[] parts = ch.split(",");
			for (String part : parts) {
				count.put(part, count.getOrDefault(part, 0) + 1);
			}
		}
		System.out.println(count);

		count.forEach((key, value) -> {
			if (value > 1) {
				System.out.println(key + " " + value);
			}
		});

		// using java 8
		String pattern = "ab,abc,abc,ab,abz,abc";
		List<String> collect = Arrays.stream(pattern.split(","))
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream()
				.filter(a -> a.getValue() > 1).map(Map.Entry::getKey).collect(Collectors.toList());
		System.out.println(collect);
	}

}
