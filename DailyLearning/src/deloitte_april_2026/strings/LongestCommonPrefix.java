package deloitte_april_2026.strings;

public class LongestCommonPrefix {
	public static void main(String[] args) {
		String[] str = { "flower", "flow", "flight" };

		System.out.println(longestCommonPrefix(str));
	}

	private static String longestCommonPrefix(String[] str) {

		String first = str[0];

		for (int i = 0; i < first.length(); i++) {
			char ch = first.charAt(i);

			for (int j = 1; j < str.length; j++) {
				if (str[j].charAt(i) != ch || i >= str[j].length()) {
					return first.substring(0, i);
				}
			}
		}

		return first;
	}

}
