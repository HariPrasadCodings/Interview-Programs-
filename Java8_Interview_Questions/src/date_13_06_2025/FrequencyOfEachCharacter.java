package date_13_06_2025;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FrequencyOfEachCharacter {
	public static void main(String[] args) {
		String s = "daivalasriram";

		Map<Character, Long> frequency = s.chars().mapToObj(c -> (char) c)
				.collect(Collectors.groupingBy(Function.identity(),
						Collectors.counting()));
		System.out.println(frequency);
	}

}
