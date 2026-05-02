package java8features;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FindOccuranceOfEachCharacter {
	public static void main(String[] args) {
		String s = "hariprasad";

		// first non-repeat character
		s.chars().mapToObj(c -> (char) c).filter(ch -> s.indexOf(ch) == s.lastIndexOf(ch)).findFirst()
				.ifPresent(System.out::println);

		// approach: 1
		Map<String, Long> collect = Arrays.stream(s.split(""))
				.collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));
		System.out.println(collect);

		// approach: 2
		Map<Object, Long> collect2 = s.chars().mapToObj(c -> (char) c)
				.collect(Collectors.groupingBy(c -> c, LinkedHashMap::new, Collectors.counting()));
		System.out.println(collect2);

		// traditional approach: 3
		Map<String, Integer> valueCount = new HashMap<>();
		String[] eachWord = s.split("");
		for (String value : eachWord) {
			valueCount.put(value, valueCount.getOrDefault(value, 0) + 1);
		}
		System.out.println(valueCount);

		// find all duplicates from given String
		List<String> duplicates = collect.entrySet().stream().filter(x -> x.getValue() > 1).map(Map.Entry::getKey)
				.collect(Collectors.toList());
		System.out.println(duplicates);

		// find unique elements in given String
		List<String> uniqueElements = collect.entrySet().stream().filter(x -> x.getValue() == 1).map(Map.Entry::getKey)
				.collect(Collectors.toList());
		System.out.println(uniqueElements);

		// find first non-repeat element from a given string
		String firstNonRepeat = collect.entrySet().stream().filter(x -> x.getValue() == 1).map(Map.Entry::getKey)
				.findFirst().get();
		System.out.println(firstNonRepeat);

		// find second highest number from given array
		int[] arr = { 5, 9, 11, 2, 8, 21, 1 }; // ans : 11
		int secondHighest = Arrays.stream(arr).boxed().sorted(Comparator.reverseOrder()).skip(1).findFirst().get();
		System.out.println("SecondHighest: " + secondHighest);

		// find second lowest number from above array // ans : 2
		Integer secondLowest = Arrays.stream(arr).boxed().sorted().skip(1).findFirst().get();
		System.out.println("SecondLowest: " + secondLowest);

		// find longest String from given array
		String[] tools = { "Java", "Spring", "Hibernate", "SpringBoot", "Microservices", "WebServices" };
		// approach: 1
		String largestString = Arrays.stream(tools).max(Comparator.comparing(String::length)).get();
		// approach: 2
		String largestStringUsingreduce = Arrays.stream(tools)
				.reduce((word1, word2) -> word1.length() > word2.length() ? word1 : word2).get();
		System.out.println(largestString);
		System.out.println(largestStringUsingreduce);

		// find all elements from array who starts with 1
		List<String> numberStartswith1 = Arrays.stream(arr).boxed().map(k -> k + "").filter(num -> num.startsWith("1"))
				.collect(Collectors.toList());
		System.out.println(numberStartswith1);

		// String.join method example
		List<String> numbers = Arrays.asList("1", "2", "3", "4");
		System.out.println(String.join(",", numbers));

		// skip & limit method use case
		IntStream.rangeClosed(1, 10).skip(1).limit(8).forEach(System.out::println);
	}

}
