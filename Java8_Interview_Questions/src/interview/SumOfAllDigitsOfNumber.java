package interview;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SumOfAllDigitsOfNumber {
	public static void main(String[] args) {
		int number = 123456;
		// approach: 1
		int sum = 0;
		while (number != 0) {
			int digit = number % 10;
			sum = sum + digit;
			number = number / 10;
		}
		System.out.println("Sum: " + sum);

		String n = "123456";
		// approach: 2
		Integer summing = Stream.of(String.valueOf(n).split("")).collect(Collectors.summingInt(Integer::parseInt));
		System.out.println(summing);
	}

}
