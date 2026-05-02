package coforge;

public class ConsecutiveCharacterCount {
	public static void main(String[] args) {
		String s = "aaabbccddeaaa";

		findConsecutiveCharCount(s);
	}

	private static void findConsecutiveCharCount(String str) {
		int count = 1;
		StringBuilder result = new StringBuilder();

		for (int i = 1; i < str.length(); i++) {
			if (str.charAt(i) == str.charAt(i - 1)) {
				count++;
			} else {
				result.append(str.charAt(i - 1)).append(count);
				count = 1;
			}
		}

		result.append(str.charAt(str.length() - 1)).append(count);

		System.out.println("Original String: " + str);
		System.out.println("Consecutive Character Count: " + result.toString());
	}

}
