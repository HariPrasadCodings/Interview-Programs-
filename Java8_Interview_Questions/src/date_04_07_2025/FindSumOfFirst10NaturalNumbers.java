package date_04_07_2025;

import java.util.stream.IntStream;

public class FindSumOfFirst10NaturalNumbers {
	public static void main(String[] args) {
		int sum = IntStream.rangeClosed(1, 10).sum();
		System.out.println("Sum of 1o natural numbers: " + sum);
	}

}
