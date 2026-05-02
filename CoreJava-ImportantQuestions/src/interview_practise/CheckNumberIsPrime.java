package interview_practise;

public class CheckNumberIsPrime {
	public static void main(String[] args) {
		int n = 5;
		System.out.println(isPrime(n));
	}

	private static boolean isPrime(int n) {
		if (n < 2) {
			return false;
		}
		for (int i = 2; i <= Math.sqrt(n); i++) {
			if (n % i == 0) {
				return false;
			}
		}

		return true;
	}

}
