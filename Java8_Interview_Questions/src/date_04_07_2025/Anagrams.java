package date_04_07_2025;

import java.util.Arrays;
import java.util.List;

/**
 * Anagrams means when the characters of the string are same either ascending or
 * descending order
 */

public class Anagrams {
	public static void main(String[] args) {
		String s1 = "hari";
		String s2 = "riha";

		boolean isAnagrams = isAnagrams(s1, s2);
		System.out.println(s1 + " and " + s2 + " are anagrams " + isAnagrams);

		System.out.println(isAnagramsUsingJava8(s1, s2));
	}

	// Before java 7
	private static boolean isAnagrams(String s1, String s2) {
		if (s1.length() != s2.length()) {
			return false;
		}

		char[] charArray1 = s1.toCharArray();
		char[] charArray2 = s2.toCharArray();

		Arrays.sort(charArray1);
		Arrays.sort(charArray2);

		return Arrays.equals(charArray1, charArray2);
	}

	@SuppressWarnings("unlikely-arg-type")
	private static boolean isAnagramsUsingJava8(String s1, String s2) {
		if (s1.length() != s2.length()) {
			return false;
		}

		List<Character> list1 = s1.chars().sorted().mapToObj(c -> (char) c)
				.toList();

		List<Character> list2 = s2.chars().sorted().mapToObj(c -> (char) c)
				.toList();

		return list1.equals(list2);
	}
}
