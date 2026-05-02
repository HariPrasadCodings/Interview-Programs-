package integers;

/**
 * Q #5) Write a Java Program to find whether a number is prime or not in the
 * most efficient way?
 */
public class PrimeNumber {
	public static void main(String[] args) {
		int a = 7;
		System.out.println(isPrime(a));
	}

	/**
	 * Explanation: This function checks for divisibility using small primes and
	 * then iterates through potential factors up to the square root of the number,
	 * checking divisibility at 6k ± 1 intervals to efficiently determine if a
	 * number is prime.
	 */
	private static boolean isPrime(int n) {
		if (n <= 1)
			return false;
		if (n <= 3)
			return true;

		if (n % 2 == 0 || n % 3 == 0)
			return false;
		for (int i = 5; i * i <= n; i = i + 6) {
			if (n % i == 0 || n % (i + 2) == 0)
				return false;
		}
		return true;
	}

}
