package interview.streams;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupTheListAndFindOccurances {
	public static void main(String[] args) {
		List<String> strings = List.of("apple", "cat", "banana", "bat", "dog");
		Map<Integer, Long> collect = strings.stream()
				.collect(Collectors.groupingBy(String::length, Collectors.counting()));
		System.out.println(collect);
	}

}
