package practise;

import java.util.Arrays;

public class NumberStartsWithTwo {
	public static void main(String[] args) {
		int[] numbers = { 13, 65, 43, 21, 78, 20, 54, 22 };

		Arrays.stream(numbers).boxed().map(String::valueOf).filter(num -> num.startsWith("2"))
				.forEach(System.out::println);
	}

}
