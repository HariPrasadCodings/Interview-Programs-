package practise;

public class CheckGivenStringPalindromeOrNot {
	public static void main(String[] args) {
		String s = "A man, a plan, a canal: panama";
		System.out.println(isPalindrome(s));
	}

	// approach:1
	/*
	 * private static boolean isPalindrome(String s) { s =
	 * s.replaceAll("[^A-Za-z0-9]", "").toLowerCase(); String reversed = new
	 * StringBuilder(s).reverse().toString(); if (s.equals(reversed)) { return
	 * true; } else { return false; } }
	 */

	// approach: 2
	/*
	 * private static boolean isPalindrome(String s) { s =
	 * s.toLowerCase().replaceAll("[^a-z0-9]", ""); int left = 0; int right =
	 * s.length() - 1;
	 * 
	 * while (left < right) { if (s.charAt(left) != s.charAt(right)) { return
	 * false; } left++; right--; } return true; }
	 */

	// approach: 3
	private static boolean isPalindrome(String s) {

		int left = 0;
		int right = s.length() - 1;

		while (left < right) {
			// skip non-alphanumeric characters from left
			while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
				left++;
			}

			while (left < right
					&& !Character.isLetterOrDigit(s.charAt(right))) {
				right--;
			}
			if (Character.toLowerCase(s.charAt(left)) != Character
					.toLowerCase(s.charAt(right))) {
				return false;
			}
			left++;
			right--;
		}

		return true;

	}

}
