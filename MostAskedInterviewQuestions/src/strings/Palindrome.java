package strings;

public class Palindrome {
	public static void main(String[] args) {
		String input = "malayalam";
		System.out.println(isPalindrome(input));
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
