package basics;

public class FactorialOfGivenNumber {
	public static void main(String[] args) {
		int n = 5;
		System.out.println("Factorial of " + n + " is: " + factorial(n));
	}

	private static int factorial(int n) {
		if (n == 0 || n == 1) {
			return n;
		}
		return n * factorial(n - 1);
	}
}
