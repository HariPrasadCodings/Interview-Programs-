package interview;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Max3and3MinNumbersFromList {
	public static void main(String[] args) {
		List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		// 3 min elements
		integers.stream().sorted().limit(3).forEach(System.out::println);
		System.out.println();
		// 3 max elements
		integers.stream().sorted(Comparator.reverseOrder()).limit(3)
				.forEach(System.out::println);
	}
}
