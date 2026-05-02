package java_interview_questions;

public class PalindromCheckForStringAndNumber {

	public static void main(String[] args) {
		String s = "malayalam";
		String reversed = new StringBuilder(s).reverse().toString();
		if (s.equals(reversed)) {
			System.out.println(s + " is palindrome");
		} else {
			System.out.println(s + " is not a palindrome");
		}

		Integer n = 1222;
		String originalNumber = Integer.toString(n);
		String reversedNumber = new StringBuilder(originalNumber).reverse().toString();
		if (originalNumber.equals(reversedNumber)) {
			System.out.println(n + " is palindrome");
		} else {
			System.out.println(n + " is not palindrome");
		}

	}
}
