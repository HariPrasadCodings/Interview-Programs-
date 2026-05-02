package java_interview_questions;

public class FindFactorial {
	public static void main(String[] args) {
		int n = 5;
		int fact = findFactorial(n);
		System.out.println("Factorial of " + n + " is: " + fact);
	}

	private static int findFactorial(int n) {
		if (n == 0 || n == 1) {
			return 1;
		}

		return n * findFactorial(n - 1);
	}

}
