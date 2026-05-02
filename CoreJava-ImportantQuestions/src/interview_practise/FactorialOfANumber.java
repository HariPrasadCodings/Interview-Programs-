package interview_practise;

public class FactorialOfANumber {
	public static void main(String[] args) {
		int n = 5;
		findFactorial(n);
	}

	private static void findFactorial(int n) {
		int factorial = 1;

		for (int i = 1; i <= n; i++) {
			factorial = factorial * i;
		}
		System.out.println("Factorial of 5 is :" + factorial);
	}

}
