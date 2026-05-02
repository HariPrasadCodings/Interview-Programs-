package monocept;

import java.util.Arrays;

public class FindOccuranceOfGivenCharacter {
	public static void main(String[] args) {
		String s = "welcome to the monocept";
		char c = 'o'; // 4

		long count = s.chars().filter(ch -> ch == c).count();
		System.out.println(count);

		long count2 = Arrays.stream(s.split("")).filter(ch -> ch.equals("o")).count();
		System.out.println(count2);
	}
}
