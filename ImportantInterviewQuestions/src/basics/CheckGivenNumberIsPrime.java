package basics;

public class CheckGivenNumberIsPrime {
	public static void main(String[] args) {
		int n = 4;

		if (isPrimeNumber(n)) {
			System.out.println(n + " is a Prime Number");
		} else {
			System.out.println(n + " is not a Prime Number");
		}
	}

	private static boolean isPrimeNumber(int n) {
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
