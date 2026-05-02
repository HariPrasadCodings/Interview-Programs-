package basics;

public class NumberPalindromCheck {
	public static void main(String[] args) {
		int n = 11211;
		if (isPalindromeNumber(n)) {
			System.out.println(n + " is a Palindrome");
		} else {
			System.out.println(n + " is not a Palindrome");
		}

	}

	private static boolean isPalindromeNumber(int n) {
		int original = n;
		int reversed = 0;

		while (n > 0) {
			int digit = n % 10;
			reversed = reversed * 10 + digit;
			n = n / 10;
		}

		return original == reversed;
	}

}
