package interview;

import java.util.stream.IntStream;

public class PalindromeProgramUsingjava8 {
	public static void main(String[] args) {
		String str = "malayalam";

		boolean palindrome = IntStream.range(0, str.length() / 2)
				.noneMatch(i -> str.charAt(i) != str.charAt(str.length() - i - 1));
		System.out.println(palindrome);
	}

}
