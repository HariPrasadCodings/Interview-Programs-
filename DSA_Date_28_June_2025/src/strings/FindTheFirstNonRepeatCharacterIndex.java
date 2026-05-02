package strings;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Problem: Return the index of the first non-repeating character in a string.
 */

public class FindTheFirstNonRepeatCharacterIndex {
	public static void main(String[] args) {

		String s = "ilovejavaprogramming";
		findIndexOfFirstNonRepeatCharacter(s);

		int index = firstNonRepeatCharacterIndex(s);
		System.out.println("First non repeat character is: " + s.charAt(index)
				+ " and its index is: " + index);

	}

	// using java stream api approach
	private static void findIndexOfFirstNonRepeatCharacter(String s) {
		if (s.length() == 0)
			return;

		Optional<Character> firstNonRepeat = s.chars().mapToObj(c -> (char) c)
				.collect(Collectors.groupingBy(Function.identity(),
						LinkedHashMap::new, Collectors.counting()))
				.entrySet().stream().filter(c -> c.getValue() == 1)
				.map(Map.Entry::getKey).findFirst();

		if (firstNonRepeat.isPresent()) {
			char ch = firstNonRepeat.get();
			int index = s.indexOf(ch);
			System.out.println("First Non Repeat Character is: " + ch
					+ " And its index is: " + index);
		}
	}

	// using traditional approach
	private static int firstNonRepeatCharacterIndex(String s) {
		if (s.length() == 0)
			return 0;

		Map<Character, Integer> map = new HashMap<>();

		for (char ch : s.toCharArray()) {
			map.put(ch, map.getOrDefault(ch, 0) + 1);
		}

		for (int i = 0; i < s.length(); i++) {
			if (map.get(s.charAt(i)) == 1) {
				return i;
			}
		}
		return -1;
	}

}
