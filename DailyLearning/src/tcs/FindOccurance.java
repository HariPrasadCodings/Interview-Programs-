package tcs;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FindOccurance {
	public static void main(String[] args) {
		String s = "ilovejavaprogramming";

		Map<String, Long> collect = Arrays.stream(s.split(""))
				.collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));
		System.out.println(collect);

		List<String> collect2 = collect.entrySet().stream().filter(ch -> ch.getValue() > 1).map(Map.Entry::getKey)
				.collect(Collectors.toList());
		System.out.println(collect2);

		// find second highest number
		int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 5, 15, 24, 32 };

		Optional<Integer> first = Arrays.stream(arr).boxed().sorted(Comparator.reverseOrder()).skip(1).findFirst();
		first.ifPresent(result -> System.out.println(result));

		// find largest string
		String[] words = { "Java", "Hibernate", "Spring", "Webservices", "Microservices" };
		String largest = Arrays.stream(words).reduce((word1, word2) -> word1.length() > word2.length() ? word1 : word2)
				.get();
		System.out.println(largest);

		// find the numbers start with 1

		List<String> startsWith1 = Arrays.stream(arr).boxed().map(n -> n + "").filter(num -> num.startsWith("1"))
				.collect(Collectors.toList());
		System.out.println(startsWith1);

		// Remove the duplicates from given string
		Set<Character> set = new HashSet<>();
		StringBuilder builder = new StringBuilder();

		for (char ch : s.toCharArray()) {
			if (!set.contains(ch)) {
				set.add(ch);
				builder.append(ch);
			}
		}
		System.out.println(builder);

		String collect3 = s.chars().distinct().mapToObj(ch -> String.valueOf((char) ch)).collect(Collectors.joining());
		System.out.println(collect3);

		// arrange even first and odd next
		int[] results = Stream.concat(Arrays.stream(arr).filter(n -> n % 2 == 0).boxed(),
				Arrays.stream(arr).filter(n -> n % 2 != 0).boxed()).mapToInt(Integer::intValue).toArray();
		System.out.println(Arrays.toString(results));
	}

}
