package date_13_06_2025;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class SeparateOddAndEven {
	public static void main(String[] args) {
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

		Stream.concat(numbers.stream().filter(num -> num % 2 != 0),
				numbers.stream().filter(num -> num % 2 == 0))
				.forEach(result -> System.out.println(result + " "));
	}

}
