package deloitte_april_2026.strings;

public class Anagrams {
	public static void main(String[] args) {
		String s = "silent";
		String t = "listen";
		boolean isAnagram = isAnagram(s, t);
		System.out.println(isAnagram);
	}

	// Approach : 1 optimal solution
	private static boolean isAnagram(String s, String t) {

		// Step : 1 compares the lengths
		if (s.length() != t.length())
			return false;

		// Step : 2 Create frequency array
		int[] count = new int[26];

		// Step : 3 Count characters in s and subtract for t
		for (int i = 0; i < s.length(); i++) {
			count[s.charAt(i) - 'a']++; // increment for s
			count[t.charAt(i) - 'a']--; // decrement for t
		}

		// Step : 4 check if all values are 0
		for (int c : count) {
			if (c != 0)
				return false;
		}

		return true;
	}

}
