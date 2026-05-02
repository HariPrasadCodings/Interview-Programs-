package basics;

public class StringPalindromeCheck {
	public static void main(String[] args) {
		String s = "malayalam";
		System.out.println(isPalindrome(s));

		String reversed = "";

		for (int i = s.length() - 1; i >= 0; i--) {
			reversed = reversed + s.charAt(i);
		}

		if (s.equalsIgnoreCase(reversed)) {
			System.out.println(s + " is a Palindrome");
		} else {
			System.out.println(s + " is not a Palindrome");
		}
	}

	private static boolean isPalindrome(String s) {
		int left = 0;
		int right = s.length() - 1;

		while (left < right) {
			if (s.charAt(left) != s.charAt(right)) {
				return false;
			}
			left++;
			right--;
		}
		return true;
	}

}
