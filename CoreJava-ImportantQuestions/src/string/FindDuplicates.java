package string;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

// 97 - 122 : a,b,c,d,e   .... ASCII values
// 65 - 89  : A,B,C,D,E   .... ASCII values

public class FindDuplicates {
	public static void main(String[] args) {
		String s = "code decode";
		System.out.println(findDuplicates(s)); // Time complexity : o(N2)

		System.out.println(findDuplicatesUsingMap(s));
	}

	private static Set<Character> findDuplicatesUsingMap(String s) {
		Set<Character> duplicates = new LinkedHashSet<>();

		Map<Character, Integer> map = new LinkedHashMap<>();

		for (int i = 0; i < s.length(); i++) {
			if (map.containsKey(s.charAt(i))) {
				map.put(s.charAt(i), map.get(s.charAt(i)) + 1);

			} else {
				map.put(s.charAt(i), 1);
			}
		}
		for (Map.Entry<Character, Integer> entry : map.entrySet()) {
			if (entry.getValue() > 1) {
				duplicates.add(entry.getKey());
			}
		}
		return duplicates;
	}

	private static Set<Character> findDuplicates(String s) {

		Set<Character> duplicates = new LinkedHashSet<>();

		for (int i = 0; i < s.length(); i++) { // o(n)
			for (int j = i + 1; j < s.length(); j++) { // o(N2)
				if (s.charAt(i) == s.charAt(j)) {
					duplicates.add(s.charAt(i));
				}
			}
		}

		return duplicates;
	}

}
