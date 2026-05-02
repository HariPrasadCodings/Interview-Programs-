package interview_practise;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ReverseString {
	public static void main(String[] args) {
		String s = "ABCD";

		int left = 0;
		int right = s.length() - 1;

		char[] ch = s.toCharArray();

		while (left < right) {
			char temp = ch[left];

			ch[left] = ch[right];
			ch[right] = temp;

			left++;
			right--;

		}
		System.out.println("Reversed String: " + new String(ch));

		// using java8

		String s1 = "hari";

		String reversedString = IntStream.range(0, s1.length())
				.mapToObj(i -> s1.charAt(s1.length() - 1 - i))
				.map(String::valueOf).collect(Collectors.joining());
		System.out.println(reversedString);
	}

}
