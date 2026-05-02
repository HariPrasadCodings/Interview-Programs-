package java_interview_questions;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DuplicatesInGivenString {

	public static void main(String[] args) {
		String s = "welcome to the java world";

		Map<String, Long> eachOccurance = Arrays.stream(s.split(""))
				.collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));
		System.out.println(eachOccurance);

		// find duplicates
		List<String> duplicates = eachOccurance.entrySet().stream().filter(count -> count.getValue() > 1)
				.map(Map.Entry::getKey).collect(Collectors.toList());
		System.out.println(duplicates);

	}
}
