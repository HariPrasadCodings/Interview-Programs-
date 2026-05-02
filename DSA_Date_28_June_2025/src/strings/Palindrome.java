package strings;

public class Palindrome {
	public static void main(String[] args) {
		String s1 = "racecar";
		if (isPalindrome(s1)) {
			System.out.println(s1 + " is Palindrome");
		} else {
			System.out.println(s1 + " is not a Palindrome");
		}

		String s2 = "HariPrasad";
		if (isPalindrome(s2)) {
			System.out.println(s2 + " is Palindrome");
		} else {
			System.out.println(s2 + " is not a Palindrome");
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
