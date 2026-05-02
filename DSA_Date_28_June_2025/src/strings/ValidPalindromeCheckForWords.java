package strings;

public class ValidPalindromeCheckForWords {
	public static void main(String[] args) {
		String s = "A man, a plan, a canal: Panama";

		if (isValidPalindrome(s)) {
			System.out.println(s + " is Valid");
		} else {
			System.out.println(s + " is Invalid");
		}
	}

	private static boolean isValidPalindrome(String s) {
		int left = 0;
		int right = s.length() - 1;

		while (left < right) {
			while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
				left++;
			}
			while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
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
