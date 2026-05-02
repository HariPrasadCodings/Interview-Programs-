package hcl;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FindOccuranceOfEachCharacterInString {
	public static void main(String[] args) {
		String s = "HelloWorldJAVA";

		// approach: 1
		Map<String, Long> map = Arrays.stream(s.split("")).map(String::toUpperCase)
				.collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));
		System.out.println(map);

		// approach: 2
		Map<Character, Long> frequency = s.chars().mapToObj(c -> (char) c)
				.collect(Collectors.groupingBy(l -> l, LinkedHashMap::new, Collectors.counting()));
		System.out.println(frequency);

		// approach: 3
		Map<Character, Integer> count = new LinkedHashMap<>();

		for (char ch : s.toCharArray()) {
			count.put(ch, count.getOrDefault(ch, 0) + 1);
		}
		System.out.println(count);
	}

}
