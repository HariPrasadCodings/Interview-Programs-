package coforge;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FindFirstNonRepeatingCharacterInString {
	public static void main(String[] args) {
		String str = "IAmAJavaDeveloper";

		Optional<Character> result = findFirstNonRepeatingChar(str);
		if (result.isPresent()) {
			System.out.println("First non repeat character: " + result.get());
		}
	}

	private static Optional<Character> findFirstNonRepeatingChar(String str) {
		return str.chars().mapToObj(c -> (char) c)
				.collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
				.entrySet().stream().filter(ch -> ch.getValue() == 1).map(Map.Entry::getKey).findFirst();

	}

}
