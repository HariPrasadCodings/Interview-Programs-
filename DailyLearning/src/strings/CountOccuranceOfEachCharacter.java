package strings;

import java.util.LinkedHashMap;
import java.util.Map;

public class CountOccuranceOfEachCharacter {
	public static void main(String[] args) {
		String s = "ilovejavaprograms";

		Map<Character, Integer> countofCharacter = new LinkedHashMap<>();

		for (char c : s.toCharArray()) {
			countofCharacter.put(c, countofCharacter.getOrDefault(c, 0) + 1);
		}

		System.out.println(countofCharacter);

		// find first non repeating character
		for (Map.Entry<Character, Integer> entry : countofCharacter.entrySet()) {
			if (entry.getValue() == 1) {
				entry.getKey();
			}
		}
	}

}
