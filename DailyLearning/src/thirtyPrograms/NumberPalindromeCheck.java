package thirtyPrograms;

public class NumberPalindromeCheck {
	public static void main(String[] args) {
		int n = 1221;

		int rev = 0;
		int original = n;

		while (n != 0) {
			int digit = n % 10;
			rev = rev * 10 + digit;
			n = n / 10;
		}

		if (original == rev) {
			System.out.println(original + " is a Palindrome");
		} else {
			System.out.println(original + " is not a palindrome");
		}
	}

}
