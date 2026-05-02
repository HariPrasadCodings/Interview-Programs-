package simplePrograms;

public class CheckGivenNumberIsPalindrome {

	private static boolean isPalindrome(int n) {
		if (n < 0 || (n % 10 == 0 && n != 0)) {
			return false;
		}
		int reversed = 0;
		int original = n;

		while (n > 0) {
			int digit = n % 10; // 1 2 2 1
			reversed = reversed * 10 + digit; // 1 12  122  1221
			n = n / 10; // 122 12 1 0
		}

		return original == reversed;

	}

	public static void main(String[] args) {
		int n = 1221;
		if (isPalindrome(n)) {
			System.out.println(n + " is a Palindrome");
		} else {
			System.out.println(n + " is not a Palindrome");
		}

	}

}
