package deloitte_april_2026.arrays.practise;

public class ConsecutiveCharsCount {
	public static void main(String[] args) {
		String s = "aaabbccddeaaa";
		System.out.println(findCount(s));
	}

	static String findCount(String s) {
		if (s.length() == 0) {
			return "";
		}

		StringBuilder result = new StringBuilder();
		char currChar = s.charAt(0);
		int count = 1;

		for (int i = 1; i < s.length(); i++) {
			if (s.charAt(i) == currChar) {
				count++;
			} else {
				result.append(currChar).append(count);
				currChar = s.charAt(i);
				count = 1;
			}
		}
		result.append(currChar).append(count);
		return result.toString();
	}

}
