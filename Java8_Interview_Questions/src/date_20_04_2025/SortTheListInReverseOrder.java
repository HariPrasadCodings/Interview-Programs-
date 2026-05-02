package date_20_04_2025;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class SortTheListInReverseOrder {
	public static void main(String[] args) {
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		numbers.stream().sorted(Comparator.reverseOrder())
				.forEach(System.out::println);
	}

}
