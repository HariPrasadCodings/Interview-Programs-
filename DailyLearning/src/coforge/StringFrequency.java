package coforge;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class StringFrequency {
	public static void main(String[] args) {
		String s = "swiss";

		Map<Character, Long> frequency = s.chars().mapToObj(c -> (char) c)
				.collect(Collectors.groupingBy(c -> c, LinkedHashMap::new, Collectors.counting()));
		System.out.println(frequency);

		// First non-repeating character
		frequency.entrySet().stream().filter(entry -> entry.getValue() == 1).findFirst().ifPresent(nullEntry -> {
			System.out.println("First non-repeating character: " + nullEntry.getKey());
		});
	}

}
