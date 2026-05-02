package interview;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FindLargestStringFromGivenArray {
	public static void main(String[] args) {
		String[] strings = {"Java", "Spring", "Hibernate", "Microservices",
				"Webservices"};
		String longest = "";

		for (String result : strings) {
			if (result.length() > longest.length()) {
				longest = result;
			}
		}
		System.out.println(longest);

		// using stream api
		String longestString = Arrays.stream(strings)
				.max((s1, s2) -> Integer.compare(s1.length(), s2.length()))
				.orElse("");
		System.out.println(longestString);

		// Arrays.stream(strings).reduce(, null)

		// find the numbers start with 1
		int[] arr = {1, 2, 3, 4, 5, 6, 12, 34, 54, 67, 21, 17};
		List<String> list = Arrays.stream(arr).boxed().map(x -> x + "")
				.filter(num -> num.startsWith("1")).toList();
		System.out.println(list);

		Stream.of(1, 2, 3, 4).parallel().forEach(System.out::println);

		CompletableFuture
				.supplyAsync(() -> "Hello", Executors.newFixedThreadPool(3))
				.thenApply(str -> str + " World")
				.thenAccept(System.out::println);
		Map<Character, Long> freq = "aabbc".chars().mapToObj(c -> (char) c)
				.collect(Collectors.groupingBy(c -> c, Collectors.counting()));
		System.out.println(freq);

		boolean isAnagram = Arrays.equals("listen".chars().sorted().toArray(),
				"silent".chars().sorted().toArray());
		System.out.println(isAnagram);
	}

}
