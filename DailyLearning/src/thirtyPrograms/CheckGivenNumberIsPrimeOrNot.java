package thirtyPrograms;

public class CheckGivenNumberIsPrimeOrNot {
	public static void main(String[] args) {
		int n = 2;

		if (isPrime(n)) {
			System.out.println(n + " is Prime");
		} else {
			System.out.println(n + " is not Prime");
		}
	}

	private static boolean isPrime(int n) {
		if (n <= 1)
			return false;

		for (int i = 2; i <= Math.sqrt(n); i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}

}
