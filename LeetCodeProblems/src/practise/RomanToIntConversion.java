package practise;

import java.util.HashMap;
import java.util.Map;

/**
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D
 * and M.
 * 
 * Symbol Value I 1 V 5 X 10 L 50 C 100 D 500 M 1000 For example, 2 is written
 * as II in Roman numeral, just two ones added together. 12 is written as XII,
 * which is simply X + II. The number 27 is written as XXVII, which is XX + V +
 * II.
 * 
 * Roman numerals are usually written largest to smallest from left to right.
 * However, the numeral for four is not IIII. Instead, the number four is
 * written as IV. Because the one is before the five we subtract it making four.
 * The same principle applies to the number nine, which is written as IX. There
 * are six instances where subtraction is used:
 * 
 * I can be placed before V (5) and X (10) to make 4 and 9. X can be placed
 * before L (50) and C (100) to make 40 and 90. C can be placed before D (500)
 * and M (1000) to make 400 and 900. Given a roman numeral, convert it to an
 * integer.
 */
public class RomanToIntConversion {

	public static void main(String[] args) {
//		String s1 = "IIIII";
//		int result1 = romanToInt(s1);
//		System.out.println(result1);
//
//		String s2 = "LVIII"; // 50 + 5 + 1 + 1 + 1 = 58
//		int result2 = romanToInt(s2);
//		System.out.println(result2);

		String s3 = "MCMXCIV";
		int result3 = romanToInt(s3);
		System.out.println(result3);

	}

	private static int romanToInt(String s) {
		Map<Character, Integer> map = new HashMap<>();
		map.put('I', 1);
		map.put('V', 5);
		map.put('X', 10);
		map.put('L', 50);
		map.put('C', 100);
		map.put('D', 500);
		map.put('M', 1000);

		int total = 0;

		for (int i = 0; i < s.length(); i++) {
			Integer current = map.get(s.charAt(i));
			if (i < s.length() - 1 && map.get(s.charAt(i + 1)) > current) {
				total = total - current;
			} else {
				total = total + current;
			}
		}
		return total;
	}

}
