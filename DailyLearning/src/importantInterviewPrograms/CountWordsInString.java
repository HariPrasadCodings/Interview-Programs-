package importantInterviewPrograms;

public class CountWordsInString {
	private static int count;

	public static void main(String[] args) {
		String input = "Follow Code Yatra";

		boolean inWord = false;

		for (int i = 0; i < input.length(); i++) {

			if (Character.isWhitespace(input.charAt(i))) {
				inWord = false;
			} else if (!inWord) {
				inWord = true;
				count++;

			}

		}
		System.out.println(count);

		System.out.println(countOfString(input));

		// Remove special characters
		String str = "!@$5Follow Code Yatra";
		str = str.replaceAll("[^a-zA-Z]", "");
		StringBuilder builder = new StringBuilder();
		System.out.println(str);
		for (char ch : str.toCharArray()) {
			if (Character.isLetterOrDigit(ch) || ch == ' ') {
				builder.append(ch);
			}
		}
		System.out.println(builder.toString());

	}

	// approach:1
	private static int countOfString(String s) {
		s = s.trim();
		if (s.isEmpty()) {
			return 0;
		}

		String[] words = s.split("\\s+");

		return words.length;
	}

}
