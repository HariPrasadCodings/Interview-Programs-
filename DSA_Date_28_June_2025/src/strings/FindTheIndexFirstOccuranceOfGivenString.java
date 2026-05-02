package strings;

/**
 * 28. Find the Index of the First Occurrence in a String
 * 
 * Given two strings needle and haystack, return the index of the first
 * occurrence of needle in haystack, or -1 if needle is not part of haystack.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: haystack = "sadbutsad", needle = "sad" Output: 0 Explanation: "sad"
 * occurs at index 0 and 6. The first occurrence is at index 0, so we return 0.
 * 
 * 
 * Example 2:
 * 
 * Input: haystack = "leetcode", needle = "leeto" Output: -1 Explanation:
 * "leeto" did not occur in "leetcode", so we return -1.
 */

public class FindTheIndexFirstOccuranceOfGivenString {

	public static void main(String[] args) {
		String haystack = "sadbutsad";
		String needle = "sad";

		System.out.println(findTheIndex(haystack, needle));
	}

	private static int findTheIndex(String haystack, String needle) {
		int n = haystack.length();
		int m = needle.length();

		if (m > n)
			return -1;

		for (int i = 0; i < n - m; i++) {
			if (haystack.substring(i, i + m).equals(needle)) {
				return i;
			}
		}
		return -1;
	}

}
