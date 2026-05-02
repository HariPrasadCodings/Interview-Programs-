package java8features;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PrintMultipleOf5 {
	public static void main(String[] args) {
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
		// Multiplying each number with 5
		List<Integer> multiple = numbers.stream().map(n -> n * 5).collect(Collectors.toList());
		System.out.println(multiple);

		// print numbers which are divisible by 5
		List<Integer> divisibleBy5 = numbers.stream().filter(n -> n % 5 == 0).collect(Collectors.toList());
		System.out.println(divisibleBy5);
	}

}
