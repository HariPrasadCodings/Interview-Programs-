package java_interview_questions;

public class PrintPrimeNumbers {
	public static void main(String[] args) {
		int start = 50;
		int end = 100;
		int count = 0;

		for (int i = start; i <= end; i++) {
			if (isPrime(i)) {
				System.out.print(i + " ");
				count++;
			}
		}
		System.out.println("\nTotal count of prime numbers: " + count);
	}

	private static boolean isPrime(int num) {
		if (num <= 1) {
			return false;
		} else {
			for (int i = 2; i <= Math.sqrt(num); i++) {
				if (num % i == 0) {
					return false;
				}
			}
		}
		return true;

	}

}
