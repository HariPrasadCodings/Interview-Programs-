package importantInterviewPrograms;

import java.util.stream.Collectors;

public class RemoveWhiteSpacesFromStrings {
	public static void main(String[] args) {
		String s = "Follow Code Yatra";
		// s = s.replaceAll(" ", "");
		// String s1 = s.replaceAll("\\s", "");

		StringBuilder builder = new StringBuilder();
		for (char c : s.toCharArray()) {
			if (c != ' ') {
				builder.append(c);
			}
		}

		System.out.println(builder.toString());

		String collect = s.chars().filter(c -> c != ' ')
				.mapToObj(c -> String.valueOf((char) c))
				.collect(Collectors.joining());
		System.out.println(collect);
	}
}
