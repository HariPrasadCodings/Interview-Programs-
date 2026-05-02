package practise;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class RemoveDuplicates {
	public static void main(String[] args) {
		String s = "HariPrasad"; // a , r are duplicates

		System.out.println(removeDuplicates(s));

		System.out.println(removeDuplicatesUsingJava8(s));
	}

	private static String removeDuplicates(String s) {
		Set<Character> set = new HashSet<>();
		StringBuilder buffer = new StringBuilder();

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (set.add(c)) {
				buffer.append(c);
			}
		}
		return buffer.toString();
	}

	// using java8
	private static String removeDuplicatesUsingJava8(String s) {
		return s.chars().mapToObj(c -> (char) c).distinct().map(String::valueOf).collect(Collectors.joining());
	}
}
