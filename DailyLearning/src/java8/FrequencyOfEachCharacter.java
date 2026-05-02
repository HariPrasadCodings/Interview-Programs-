package java8;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FrequencyOfEachCharacter {
	public static void main(String[] args) {
		String s = "saiakruthipatel";

		// Approach : 1 Using Java 8 streams
		Map<Character, Long> frequency = s.chars().mapToObj(c -> (char) c)
				.collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));
		System.out.println(frequency);

		// Approach : 2
		Map<Object, Long> freq = Arrays.stream(s.split(""))
				.collect(Collectors.groupingBy(a -> a, LinkedHashMap::new, Collectors.counting()));
		System.out.println(freq);

		// Approach : 3 Traditional approach
		Map<Character, Integer> map = new LinkedHashMap<>();
		for (char c : s.toCharArray()) {
			map.put(c, map.getOrDefault(c, 0) + 1);
		}
		System.out.println(map);
	}
}
