package java_interview_questions;

public class CheckGivenNumberIsPrime {
	public static void main(String[] args) {
		int n = 2;
		boolean isPrime = isPrimeNumber(n);
		if (isPrime) {
			System.out.println(n + " is a PrimeNumber");
		} else {
			System.out.println(n + " is not a Primenumber");
		}
	}

	private static boolean isPrimeNumber(int n) {
		if (n <= 1) {
			return false;
		} else {
			for (int i = 2; i <= Math.sqrt(n); i++) {
				if (n % i == 0) {
					return false;
				}
			}
		}
		return true;
	}
}
