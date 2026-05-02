package strings_11_08_2025;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ValidAnagrams {
	public static void main(String[] args) {
		String s = "anagram";
		String t = "nagaram";

		if (isValidAnagram(s, t)) {
			System.out.println("Strings are anagrams");
		} else {
			System.out.println("Strings are not anagarmas");
		}

		if (isValidAnagramsUsingHashMap(s, t)) {
			System.out.println("Strings are anagrams");
		}
	}

	// Approach: 1 Using Sorting
	// TC: O(nlogn)+ O(mlogm)
	// SC: O(n+n)
	static boolean isValidAnagram(String s, String t) {
		if (s.length() != t.length()) {
			return false;
		}

		char[] ch1 = s.toCharArray();
		char[] ch2 = t.toCharArray();

		Arrays.sort(ch1);
		Arrays.sort(ch2);

		return Arrays.equals(ch1, ch2);
	}

	// Approach: 2 Using HashMap
	// TC: O(m+n) SC: O(K)
	static boolean isValidAnagramsUsingHashMap(String s, String t) {
		if (s.length() != t.length()) {
			return false;
		}
		// Create a HashMap to store character frequencies
		Map<Character, Integer> charCount = new HashMap<>();

		// count frequency of each character in String s
		for (char ch1 : s.toCharArray()) {
			charCount.put(ch1, charCount.getOrDefault(ch1, 0) + 1);
		}
		// count frequency of each character in String t
		for (char ch2 : t.toCharArray()) {
			charCount.put(ch2, charCount.getOrDefault(ch2, 0) - 1);
		}

		// check if all frequencies are zero
		for (var pair : charCount.entrySet()) {
			if (pair.getValue() != 0) {
				return false;
			}
		}

		return true;
	}

	// Approach: 3 Frequency Array
    // O(m+n)  SC: O(1)
	static boolean isValidAnagramsUsingFrequencyArray(String s, String t) {
		if (s.length() != t.length()) {
			return false;
		}

		int[] freq = new int[26];

		for (int i = 0; i < s.length(); i++) {
			freq[s.charAt(i) - 'a']++;
		}

		for (int i = 0; i < t.length(); i++) {
			freq[s.charAt(i) - 'a']--;
		}

		for (int count : freq) {
			if (count != 0) {
				return false;
			}
		}
		return true;

	}

}
