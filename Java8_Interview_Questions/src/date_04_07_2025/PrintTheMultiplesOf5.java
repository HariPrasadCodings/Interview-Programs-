package date_04_07_2025;

import java.util.Arrays;
import java.util.List;

public class PrintTheMultiplesOf5 {
	public static void main(String[] args) {
		List<Integer> numbers = Arrays.asList(1, 4, 5, 7, 8, 9, 15, 12, 10);

		numbers.stream().filter(num -> num % 5 == 0)
				.forEach(System.out::println);
	}

}
