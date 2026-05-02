package date_04_07_2025;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class FirstNonRepeatedChar {
	public static void main(String[] args) {
		String str = "aabbc";
		System.out.println("First non-repeated: " + findFirstNonRepeated(str));
	}

	private static char findFirstNonRepeated(String str) {
		return str.chars().mapToObj(c -> (char) c)
				.collect(Collectors.groupingBy(c -> c, LinkedHashMap::new,
						Collectors.counting()))
				.entrySet().stream().filter(ch -> ch.getValue() == 1)
				.map(Map.Entry::getKey).findFirst().orElse(null);
	}

}
