package tcs;

import java.util.Arrays;
import java.util.List;

public class CountTheSpecialCharacters {
	public static void main(String[] args) {
		String input = "Ja@#V&a!";
		int count = 0;
		for (int i = 0; i < input.length(); i++) {
			char ch = input.charAt(i);
			if (!Character.isLetterOrDigit(ch) && !Character.isWhitespace(ch)) {
				count++;
			}
		}
		System.out.println(count);

		for (int i = 0; i < input.length(); i++) {
			char ch = input.charAt(i);
			if (ch == '@' || ch == '#' || ch == '&' || ch == '!') {
				count++;
			}
		}
		System.out.println("Special Characters: " + count);

		// Using java8 streams
		long countOfSpecialCharacters = input.chars().filter(ch -> ch == '@' || ch == '#' || ch == '&' || ch == '!')
				.count();
		System.out.println("Special Characters: " + countOfSpecialCharacters);

		// Using for-each loop

		int[] size = { 0 };
		List<Character> special = Arrays.asList('@', '&', '!', '#');
		input.chars().mapToObj(c -> (char) c).forEach(ch -> {
			if (special.contains(ch)) {
				size[0]++;
			}
		});
		System.out.println("Special Characters: " + size[0]);

	}

}
