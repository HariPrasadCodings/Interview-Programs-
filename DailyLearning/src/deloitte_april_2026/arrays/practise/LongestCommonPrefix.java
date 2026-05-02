package deloitte_april_2026.arrays.practise;

public class LongestCommonPrefix {
	public static void main(String[] args) {
		String[] str = { "flower", "flow", "flight" };
		
		System.out.println(longestPrefix(str));
	}

	static String longestPrefix(String[] str) {
		if (str.length == 0 || str == null) {
			return "";
		}

		String first = str[0];

		for (int i = 0; i < first.length(); i++) {
			char ch = first.charAt(i);

			for (int j = 1; j < str.length; j++) {
				if (i >= str[j].length() || str[j].charAt(i) != ch) {
					return first.substring(0, i);
				}
			}
		}

		return first;
	}

}
