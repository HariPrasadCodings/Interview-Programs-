package strings;

import java.util.Objects;

public class SeparateCharactersAndNumbers {
	public static void main(String[] args) {
		String s = "ACB133DFH";
		separateCharactersAndNumbers(s);

	}

	private static void separateCharactersAndNumbers(String s) {
		if (Objects.isNull(s) || s.length() == 0) {
			return;

		}
		StringBuilder letters = new StringBuilder();
		StringBuilder numbers = new StringBuilder();

		for (char ch : s.toCharArray()) {
			if (Character.isLetter(ch)) {
				letters.append(ch);
			} else if (Character.isDigit(ch)) {
				numbers.append(ch);
			}
		}

		System.out.println("Letters: " + letters);
		System.out.println("Numbers: " + numbers);
	}

}
