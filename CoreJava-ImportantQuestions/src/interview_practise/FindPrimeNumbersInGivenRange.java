package interview_practise;

import java.util.stream.IntStream;

public class FindPrimeNumbersInGivenRange {
	public static void main(String[] args) {
		int start = 2;
		int end = 50;

		System.out.println(
				"Prime numbers between " + start + " to " + end + " are:");

		for (int num = start; num <= end; num++) {
			if (isPrime(num)) {
				System.out.print(num + " ");
			}
		}
		System.out.println();
		// using java 8
		IntStream.rangeClosed(start, end)
				.filter(FindPrimeNumbersInGivenRange::isPrimeNumber)
				.forEach(num -> System.out.print(num + " "));
	}

	private static boolean isPrime(int num) {
		if (num < 2)
			return false;

		for (int i = 2; i <= Math.sqrt(num); i++) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}

	// using java 8

	private static boolean isPrimeNumber(int num) {
		return IntStream.rangeClosed(2, (int) Math.sqrt(num))
				.noneMatch(i -> num % i == 0);
	}

}
