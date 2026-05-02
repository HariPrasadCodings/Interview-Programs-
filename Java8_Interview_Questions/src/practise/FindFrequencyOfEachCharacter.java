package practise;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FindFrequencyOfEachCharacter {
	public static void main(String[] args) {
		String s = "ilovejavaprogramming";

		Map<Character, Long> frequency = s.chars().mapToObj(c -> (char) c)
				.collect(Collectors.groupingBy(Function.identity(),
						LinkedHashMap::new, Collectors.counting()));
		System.out.println(frequency);

		// output : {i=2, l=1, o=2, v=2, e=1, j=1, a=3, p=1, r=2, g=2, m=2, n=1}

		// find duplicate characters
		List<String> duplicateCharacters = Arrays.stream(s.split(""))
				.collect(Collectors.groupingBy(Function.identity(),
						LinkedHashMap::new, Collectors.counting()))
				.entrySet().stream().filter(count -> count.getValue() > 1)
				.map(Map.Entry::getKey).toList();
		System.out.println(duplicateCharacters);

		// find first non repeat character
		Character nonRepeatCharacter = s.chars().mapToObj(c -> (char) c)
				.collect(Collectors.groupingBy(Function.identity(),
						LinkedHashMap::new, Collectors.counting()))
				.entrySet().stream().filter(c -> c.getValue() == 1)
				.map(Map.Entry::getKey).findFirst().orElse('h');
		System.out.println(nonRepeatCharacter);
	}

}
