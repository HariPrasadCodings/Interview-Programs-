package interview_practise;

import java.util.List;

public class CheckNumberIsPalindrome {
	public static void main(String[] args) {
		int n = 12321;
		if (isPalindrome(n)) {
			System.out.println(n + " is a palindrome");
		} else {
			System.out.println(n + " is not palindrome");
		}

		if (isPalindromeUsingJava8(n)) {
			System.out.println(n + " is a palindrome");
		} else {
			System.out.println(n + " is not palindrome");
		}
	}

	private static boolean isPalindrome(int n) {

		int original = n;
		int reversed = 0;

		while (n > 0) {
			int digit = n % 10;
			reversed = reversed * 10 + digit;
			n = n / 10;
		}

		return original == reversed;
	}

	// using java 8

	private static boolean isPalindromeUsingJava8(int n) {
		int original = n;
		int reversed = Integer
				.parseInt(new StringBuilder(String.valueOf(n)).reverse().toString());

		return original == reversed;
	}
}
