package deloitte_april_2026.arrays.practise;

import java.util.HashMap;
import java.util.Map;

public class LongestSubString {
	public static void main(String[] args) {
		String str = "abcabcbb";
		System.out.println(longestSubString(str));
		
		String result = STR
	}

	static String longestSubString(String s) {

		Map<Character, Integer> map = new HashMap<>();

		int left = 0;
		int maxLength = 0;
		int startindex = 0;

		for (int right = 0; right < s.length(); right++) {
			char ch = s.charAt(right);

			if (map.containsKey(ch)) {
				left = Math.max(left, map.get(ch) + 1);
			}
			map.put(ch, right);
			// maxLength = Math.max(maxLength, right - left + 1);
			if (right - left + 1 > maxLength) {
				maxLength = right - left + 1;
				startindex = left;
			}
		}
		return s.substring(startindex, maxLength - startindex);
	}

}
