package interview;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FrequencyOfEachCharacterInString {
	public static void main(String[] args) {
		String input = "ilovejavaprogramming";

		Map<Character, Long> frequency = input.chars().mapToObj(c -> (char) c)
				.collect(Collectors.groupingBy(Function.identity(),
						LinkedHashMap::new, Collectors.counting()));
		System.out.println(frequency);

		List<Character> duplicates = frequency.entrySet().stream()
				.filter(x -> x.getValue() > 1).map(Map.Entry::getKey).toList();
		System.out.println(duplicates);

		// Approach : 2 Traditional approach

		Map<Character, Integer> map = new LinkedHashMap<>();

		char[] ch = input.toCharArray();

		for (char result : ch) {
			map.put(result, map.getOrDefault(result, 0) + 1);
		}
		System.out.println(map);

		// Finding duplicates from given string
		System.out.println("Duplicate characters: ");
		for (Map.Entry<Character, Integer> entry : map.entrySet()) {
			if (entry.getValue() > 1) {
				System.out.print(entry.getKey() + " ");
			}
		}
	}

}
