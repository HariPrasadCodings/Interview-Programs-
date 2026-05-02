package strings;

public class PalindromeCheck {
	public static void main(String[] args) {
		String s = "malayalam";
		String reversed = "";

		// approach: 1
		for (int i = s.length() - 1; i >= 0; i--) {
			reversed = reversed + s.charAt(i);
		}
		if (s.equals(reversed)) {
			System.out.println(s + " is a Palindrome");
		} else {
			System.out.println(s + " is not a Palindrome");
		}
		System.out.println();
		// approach: 2
		boolean isPalindrome = true;
		int n = s.length();

		for (int i = 0; i < n / 2; i++) {
			if (s.charAt(i) != s.charAt(n - i - 1)) {
				isPalindrome = false;
			}
		}
		if (isPalindrome) {
			System.out.println("String is Palindrome");
		} else {
			System.out.println("String is not a Palindrome");
		}

		System.out.println();
		// approach: 3
		String rever = new StringBuilder(s).reverse().toString();
		if (s.equals(rever)) {
			System.out.println(s + " is palindrome");
		} else {
			System.out.println("String is not a Palindrome");
		}
	}

}
