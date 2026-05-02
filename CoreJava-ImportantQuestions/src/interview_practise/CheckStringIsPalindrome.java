package interview_practise;

import java.util.stream.IntStream;

public class CheckStringIsPalindrome {
	public static void main(String[] args) {
		String s = "racecar";

		System.out.println(isPalindrome(s));
		System.out.println(isPalindromeWithJava8(s));

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

	// using java 8
	private static boolean isPalindromeWithJava8(String s) {
		return IntStream.range(0, s.length() / 2)
				.allMatch(i -> s.charAt(i) == s.charAt(s.length() - 1 - i));
	}

}
