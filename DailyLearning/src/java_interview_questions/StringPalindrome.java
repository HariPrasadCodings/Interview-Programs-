package java_interview_questions;

public class StringPalindrome {
	public static void main(String[] args) {
		String input = "DAD";
		boolean isPalindrome = isPalindrome(input);
		if (isPalindrome) {
			System.out.println(input + " is a Palindrome");
		} else {
			System.out.println(input + "  is not a Palindrome");
		}
	}

	private static boolean isPalindrome(String input) {
		String reverse = "";
		if (input.length() == 0 || input == null) {
			return false;
		} else {
			for (int i = input.length() - 1; i >= 0; i--) {
				reverse = reverse + input.charAt(i);
			}
		}
		return input.equals(reverse);
	}

}
