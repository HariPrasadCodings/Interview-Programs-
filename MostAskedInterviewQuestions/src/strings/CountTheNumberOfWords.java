package strings;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Q #3) Write a Java Program to count the number of words in a string using
 * HashMap.
 */

public class CountTheNumberOfWords {
	public static void main(String[] args) {
		String input = "Hari Prasad Kathi";
		Map<String, Integer> results = countWords(input);
		System.out.println(results);
	}

	/**
	 * Explanation: This solution splits the input string into words using a space
	 * delimiter, then uses a HashMap to count the occurrences of each word. The
	 * getOrDefault method is used to simplify the counting logic.
	 */
	public static Map<String, Integer> countWords(String input) {
		Map<String, Integer> wordsCount = new LinkedHashMap<>();
		String[] words = input.split("\\s+");

		for (String word : words) {
			wordsCount.put(word, wordsCount.getOrDefault(word, 0) + 1);
		}
		return wordsCount;
	}

}
