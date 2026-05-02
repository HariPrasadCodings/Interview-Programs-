package dt_11_05_2025;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReversingString {
	public static void main(String[] args) {
		String s = "hari";
		System.out.println(reverse(s));
		System.out.println(reverseUsingStringBuilder(s));
		System.out.println(reverseUsingRecursion(s));
		System.out.println(reverseUsingCollections(s));

	}

	private static String reverse(String input) {
		if (input == null)
			return null;
		char[] c = input.toCharArray();
		int left = 0;
		int right = input.length() - 1; // 4-1 = 3;

		while (left < right) {
			char temp = c[left]; // h
			c[left++] = c[right]; // i
			c[right--] = temp; //
		}
		return new String(c);
	}

	// using stringbuilder
	private static String reverseUsingStringBuilder(String input) {
		if (input == null) {
			return null;
		} else {
			return new StringBuilder(input).reverse().toString();
		}
	}

	// Using recursion
	private static String reverseUsingRecursion(String input) {
		if (input == null || input.length() <= 1) {
			return input;
		} else {
			return reverseUsingRecursion(input.substring(1)) + input.charAt(0);
		}
	}

	// using collections
	private static String reverseUsingCollections(String input) {
		if (input == null) {
			return null;
		}
		List<Character> list = new ArrayList<>();
		for (char c : input.toCharArray()) {
			list.add(c);
		}
		Collections.reverse(list);
		StringBuilder builder = new StringBuilder(list.size());
		for (Character ch : list) {
			builder.append(ch);
		}
		return builder.toString();
	}

}
