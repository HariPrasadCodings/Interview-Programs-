package deloitte_april_2026.arrays.practise;

import java.util.Arrays;

public class ValidAnagram {
	public static void main(String[] args) {
		String first = "listen";
		String second = "silent";

		System.out.println(isValidAnagrams(first, second));
		System.out.println(isValid(first, second));
	}

	// Time: O(n log n) (sorting)
	// Space: O(n)
	static boolean isValidAnagrams(String s1, String s2) {
		if (s1.length() != s2.length()) {
			return false;
		}

		char[] ch1 = s1.toCharArray();
		char[] ch2 = s2.toCharArray();

		Arrays.sort(ch1);
		Arrays.sort(ch2);

		return Arrays.equals(ch1, ch2);
	}

	// Approach:2
	// Time: O(n) ✅ (best)
	// Space: O(1) (fixed array size 26)
	static boolean isValid(String s1, String s2) {
		if (s1.length() != s2.length()) {
			return false;
		}
		int[] res = new int[26];

		for (int i = 0; i < s1.length(); i++) {
			res[s1.charAt(i) - 'a']++;
			res[s1.charAt(i) - 'a']--;
		}

		for (int count : res) {
			if (count != 0) {
				return false;
			}
		}
		return true;
	}

}
