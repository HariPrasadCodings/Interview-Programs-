package java8;

import java.util.List;
import java.util.stream.IntStream;

/**
 * Write a java program for below input : aaabbccddeaaa i want the output in
 * this: a3b2c2d2e1a3, to print the consecutive characters count?
 */
public class ConsecutiveCharactersCount {
	public static void main(String[] args) {
		String input = "aaabbccddeaaa";

		String result = findConsecutiveCharactersCount(input);

		System.out.println(result); // Output: a3b2c2d2e1a3
	}

	private static String findConsecutiveCharactersCount(String input) {
		List<Character> chars = input.chars().mapToObj(c -> (char) c).toList();
		StringBuilder builder = new StringBuilder();

		int i = 0;

		while (i < chars.size()) {
			char current = chars.get(i);
			long count = IntStream.range(i, chars.size()).takeWhile(j -> chars.get(j) == current).count();

			builder.append(current).append(count);
			i += count;
		}

		return builder.toString();
	}
}
