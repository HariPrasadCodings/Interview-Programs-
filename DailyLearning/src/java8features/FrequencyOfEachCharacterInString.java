package java8features;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FrequencyOfEachCharacterInString {
	public static void main(String[] args) {
		String s = "hariprasadkathi";

		// approach: 1
		Map<String, Long> frequency = Arrays.stream(s.split(""))
				.collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));
		System.out.println(frequency);

		// approach: 2
		Map<Character, Integer> mapCount = new HashMap<>();

		for (char c : s.toCharArray()) {
			mapCount.put(c, mapCount.getOrDefault(c, 0) + 1);
		}
		System.out.println(mapCount);

		// approach: 3
		Map<Character, Long> eachCharacter = s.chars().mapToObj(c -> (char) c)
				.collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));
		System.out.println(eachCharacter);
	}

}
