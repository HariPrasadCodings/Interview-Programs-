package java_interview_questions;

public class NumberPalindrome {

	public static void main(String[] args) {
		int number = 1221;
		if (isPalindrome(number)) {
			System.out.println(number + "  is a Palindrome");
		} else {
			System.out.println(number + " is not a palindrome");
		}
	}

	private static boolean isPalindrome(int n) {
		int original = n;
		int reversed = 0;
		if (n == 0 || n <= 1) {
			return false;
		} else {
			while (n != 0) {
				int digit = n % 10;
				reversed = reversed * 10 + digit;
				n = n / 10;
			}
		}

		return original == reversed;
	}

}
