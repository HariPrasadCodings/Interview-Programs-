package date_04_07_2025;

import java.util.stream.IntStream;

public class PrintFirst10EvenNumbers {
	public static void main(String[] args) {
		IntStream.rangeClosed(2, 20).filter(n -> n % 2 == 0)
				.forEach(System.out::println);
	}
}
