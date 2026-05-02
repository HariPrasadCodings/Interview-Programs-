package date_04_07_2025;

import java.util.stream.IntStream;

public class StringReverse {
	public static void main(String[] args) {
		String s = "Java";

		System.out.println("Reversed string: " + reverse(s));
	}

	private static String reverse(String s) {

		return IntStream.range(0, s.length())
				.mapToObj(i -> s.charAt(s.length() - 1 - i))
				.collect(StringBuilder::new, StringBuilder::append,
						StringBuilder::append)
				.toString();
	}

}
