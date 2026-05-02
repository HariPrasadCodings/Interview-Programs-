package strings;

import java.util.Objects;

public class PalindromeTest {
	public static void main(String[] args) {
		String s = "racecar";

		System.out.println(isPalindrome(s));
	}

	static boolean isPalindrome(String s) {
		if (Objects.isNull(s) || s.length() == 0 ) {
			return false;
		}

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
