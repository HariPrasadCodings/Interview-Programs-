package strings;

import java.util.HashMap;
import java.util.Map;

public class RomanToIntValue {
	public static void main(String[] args) {
		String s = "III";
		String s1 = "LVIII";
		String s2 = "MCMXCIV";

		System.out.println(romanToInt(s));
		System.out.println(romanToInt(s1));
		System.out.println(romanToInt(s2));
	}

	private static int romanToInt(String s) {
		Map<Character, Integer> roman = new HashMap<>();
		roman.put('I', 1);
		roman.put('V', 5);
		roman.put('X', 10);
		roman.put('L', 50);
		roman.put('C', 100);
		roman.put('D', 500);
		roman.put('M', 1000);

		int total = 0;

		for (int i = 0; i < s.length(); i++) {
			int current = roman.get(s.charAt(i));
			// if the current value is less than next index subtract it
			if (i + 1 < s.length() && current < roman.get(s.charAt(i + 1))) {
				total = total - current;
			} else {
				total = total + current;
			}
		}
		return total;
	}

}
