package java_interview_questions;

public class FindPrimeNumberGivenRange {

	public static void main(String[] args) {
		int start = 2;
		int end = 10;
		printPrimeNumbers(start, end);
	}

	private static void printPrimeNumbers(int a, int b) {
		System.out.println("Prime Numbers from " + a + " to " + b);
		for (int i = a; i <= b; i++) {
			if (i > 1) {
				boolean isPrime = true;

				for (int j = 2; j * j <= i; j++) {
					if (i % j == 0) {
						isPrime = false;
						break;
					}
				}

				if (isPrime) {
					System.out.print(i + " ");
				}
			}
		}

	}
}
