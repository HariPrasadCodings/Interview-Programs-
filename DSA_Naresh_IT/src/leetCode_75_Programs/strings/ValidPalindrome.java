package leetCode_75_Programs.strings;

public class ValidPalindrome {
	public static void main(String[] args) {
		String s = "A man, a plan, a canal: Panama";

		String s1 = "race a car";

		System.out.println(isValidPalindrome(s));
		System.out.println(isValidPalindrome(s1));
	}

	static boolean isValidPalindrome(String s) {
		int left = 0;
		int right = s.length() - 1;

		while (left < right) {

			while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
				left++;
			}

			while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
				right--;
			}

			if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
				return false;
			}

			left++;
			right--;
		}
		return true;
	}

}
