package strings_13_08_2025;

public class RemoveAllOccurancesOfSubString {
	public static void main(String[] args) {
		String s = "daabcbaabcbc";
		String part = "abc";

		System.out.println(removeUsingMethods(s, part));

		System.out.println(removeSubStringUsingReplaceFirst(s, part));

		System.out.println(removeSubStringUsingStack(s, part));
	}

	// Approach 1: Iterative removal using String methods
	// TC: Worst case: O(n^2)
	// SC: O(n)
	static String removeUsingMethods(String s, String part) {
		while (s.contains(part)) {
			int index = s.indexOf(part);
			s = s.substring(0, index) + s.substring(index + part.length());
		}
		return s;
	}

	// Approach 2: Using String replaceFirst method
	// TC: Worst case: O(n^2) : because removing substrings multiple times
	// SC: O(n) Due to repeated string operations
	static String removeSubStringUsingReplaceFirst(String s, String part) {
		while (s.contains(part)) {
			s = s.replaceFirst(part, "");
		}
		return s;
	}

	// Approach 3: using stack to build Result(More efficient)
    // TC: O(n):Linear scan overall o(m * n)
	static String removeSubStringUsingStack(String s, String part) {
		StringBuilder builder = new StringBuilder();
		int partLength = part.length();

		for (int i = 0; i < s.length(); i++) {
			builder.append(s.charAt(i));

			if (builder.length() >= partLength && builder.substring(builder.length() - partLength).equals(part)) {
				builder.delete(builder.length() - partLength, builder.length());
			}
		}

		return builder.toString();
	}

}
