package strings;

import java.util.LinkedHashSet;
import java.util.Set;

public class RemoveDuplicateFromString {
	public static void main(String[] args) {
		String s = "hariprasad"; // duplicates: a,r // haripsd
		System.out.println(s.intern());
		System.out.println("Duplicates removed: " + removeDuplicates(s));

	}

	private static String removeDuplicates(String s) {
		Set<Character> seen = new LinkedHashSet<>();
		for (char c : s.toCharArray()) {
			seen.add(c);
		}

		StringBuilder result = new StringBuilder();
		for (char ch : seen) {
			result.append(ch);
		}

		return result.toString();
	}

}
