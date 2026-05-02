package basics;

public class FindPrimeNumbersGivenRange {
	public static void main(String[] args) {
		int start = 2;
		int end = 10;

		System.out.print(
				"Prime Numbers between " + start + " and " + end + " are – ");

		for (int i = start; i <= end; i++) {
			if (isPrime(start)) {
				System.out.print(i + " ");
			}
		}

	}

	private static boolean isPrime(int n) {
		if (n <= 1)
			return false;
		if (n == 2)
			return true;

		for (int i = n; i <= Math.sqrt(n); i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}

}
