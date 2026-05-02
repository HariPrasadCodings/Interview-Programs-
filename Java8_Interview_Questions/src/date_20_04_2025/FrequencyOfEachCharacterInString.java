package date_20_04_2025;

import java.util.LinkedHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FrequencyOfEachCharacterInString {
	public static void main(String[] args) {
		String s = "ilovejavaprogramming";
		// Map<String, Long> frequency = Arrays.stream(s.split(""))
		// .collect(Collectors.groupingBy(Function.identity(),
		// LinkedHashMap::new, Collectors.counting()));
		// System.out.println(frequency);

		// Approach:2
		LinkedHashMap<Character, Long> countOfEachCharacter = s.chars()
				.mapToObj(c -> (char) c)
				.collect(Collectors.groupingBy(Function.identity(),
						LinkedHashMap::new, Collectors.counting()));
		System.out.println(countOfEachCharacter);
	}

}
