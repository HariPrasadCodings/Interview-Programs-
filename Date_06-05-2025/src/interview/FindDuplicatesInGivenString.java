package interview;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FindDuplicatesInGivenString {
	public static void main(String[] args) {
		String s = "ilovejavaprogramming";
		printDuplicates(s);
		System.out.println();
		findDuplicates(s);
	}

	private static void printDuplicates(String s) {
		s = s.toLowerCase();
		String[] words = s.split("");
		int count;

		for (int i = 0; i < words.length; i++) {
			count = 1;
			for (int j = i + 1; j < words.length; j++) {
				if (words[i].equals(words[j])) {
					count++;
					words[j] = "0";
				}
			}
			if (count > 1 && words[i] != "0") {
				System.out.print(words[i] + " ");
			}
		}

	}

	// using java 8 approach
	private static void findDuplicates(String s) {
		Map<Character, Long> frequency = s.chars().mapToObj(c -> (char) c)
				.collect(Collectors.groupingBy(Function.identity(),
						LinkedHashMap::new, Collectors.counting()));
		List<Character> duplicates = frequency.entrySet().stream()
				.filter(x -> x.getValue() > 1).map(Map.Entry::getKey).toList();
		System.out.println(duplicates);
	}

}
