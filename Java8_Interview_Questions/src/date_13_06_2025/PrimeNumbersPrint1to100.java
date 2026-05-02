package date_13_06_2025;

import java.util.stream.IntStream;

public class PrimeNumbersPrint1to100 {
	public static void main(String[] args) {
		System.out.println("Prime numbers from 1 to 100");

		for (int i = 2; i <= 100; i++) {
			if (isPrime(i)) {
				System.out.print(i + " ");
			}
		}
		System.out.println();
		// using java 8 method call
		IntStream.rangeClosed(2, 100)
				.filter(PrimeNumbersPrint1to100::isPrimeNumber)
				.forEach(result -> System.out.print(result + " "));
	}

	private static boolean isPrime(int n) {
		if (n < 2)
			return false;
		for (int i = 2; i <= Math.sqrt(n); i++) {
			if (n % i == 0)
				return false;
		}
		return true;
	}

	// using java 8 approach
	private static boolean isPrimeNumber(int n) {
		return n > 1 && IntStream.rangeClosed(2, (int) Math.sqrt(n))
				.allMatch(i -> n % i != 0);
	}

}
