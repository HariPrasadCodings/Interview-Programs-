package arrays;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

interface Demo {
	static final int a = 20;
	int b = 20;
}

abstract class Test1 implements Demo {

}

class Test2 {
	final int a = 0;

	final static void test() {

	}
}

public class RemoveDuplicates extends Test2 {
	public static void main(String[] args) {
		int[] nums = { 1, 1, 2 };
		int[] distinct = Arrays.stream(nums).distinct().toArray();
		System.out.println(Arrays.toString(distinct));
		List<String> words = Arrays.asList("Java", "Streams");
		List<Integer> lengths = words.stream().map(String::length).collect(Collectors.toList()); // [4, 7]
		System.out.println(lengths);

		List<List<Integer>> nestedList = Arrays.asList(Arrays.asList(1, 2), Arrays.asList(3, 4));
		List<Integer> flattenedList = nestedList.stream().flatMap(List::stream).collect(Collectors.toList()); // [1, 2,
																												// 3, 4]
		System.out.println(flattenedList);

		List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
		int sum = numbers.stream().reduce(0, Integer::sum); // 10
		System.out.println(sum);

		numbers.parallelStream().forEach(System.out::println);

		String str = "hello";
		Map<Character, Long> charCount = str.chars().mapToObj(c -> (char) c)
				.collect(Collectors.groupingBy(d -> d, Collectors.counting()));
		System.out.println(charCount);

		String str1 = "hari";
		String str2 = "har";
		System.out.println(isAnagram(str1, str2));

	}

	static boolean isAnagram(String str1, String str2) {
		char[] arr1 = str1.toCharArray();
		char[] arr2 = str2.toCharArray();
		Arrays.sort(arr1);
		Arrays.sort(arr2);
		return Arrays.equals(arr1, arr2);
	}

}
