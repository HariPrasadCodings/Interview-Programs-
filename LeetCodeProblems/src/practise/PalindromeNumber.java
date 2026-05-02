package practise;

/**
 * Given an integer x, return true if x is a palindrome , and false otherwise.
 */
public class PalindromeNumber {
	public static void main(String[] args) {
		int n = 1221;
		if (isPalindrome(n)) {
			System.out.println(n + " is a Palindrome");
		} else {
			System.out.println(n + " is not a Palindrome");
		}

	}

	private static boolean isPalindrome(int n) {
		int reversed = 0;
		int original = n;

		while (n > 0) {
			int digit = n % 10;
			reversed = reversed * 10 + digit;
			n = n / 10;
		}

		return reversed == original;
	}

}
