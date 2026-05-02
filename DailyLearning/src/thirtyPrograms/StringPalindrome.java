package thirtyPrograms;

public class StringPalindrome {
	public static void main(String[] args) {
		String s = "DAD";

		boolean result = reverse(s);
		System.out.println(result);
	}

	private static boolean reverse(String s) {

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
