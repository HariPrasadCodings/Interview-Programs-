package practise;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FrequencyOfEachCharacter {
	public static void main(String[] args) {
		String s = "ilovejava";

		Map<String, Long> frequency = Arrays.stream(s.split(""))
				.collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));
		System.out.println(frequency);

		// approach : 2
		Map<Character, Integer> count = new LinkedHashMap<>();

		for (char c : s.toCharArray()) {
			count.put(c, count.getOrDefault(c, 0) + 1);
		}
		System.out.println(count);

		// approach: 3
		LinkedHashMap<Character, Long> countOfEachCharacter = s.chars().mapToObj(c -> (char) c)
				.collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));
		System.out.println(countOfEachCharacter);
	}

}
