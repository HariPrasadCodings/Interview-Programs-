package importantInterviewPrograms;

public class ReverseEachWord {
	public static void main(String[] args) {
		String s = "Follow Code Yatra";

		String reverse = reverseWords(s);
		System.out.println(reverse);
	}

	// private static String reverseWords(String s) {
	// StringBuilder builder = new StringBuilder();
	// String[] words = s.split(" ");
	// for (String word : words) {
	// StringBuilder sb = new StringBuilder(word);
	// builder.append(sb.reverse()).append(" ");
	// }
	// return builder.toString().trim();
	// }

	// approach:2
	private static String reverseWords(String s) {
		String[] words = s.split(" ");
		StringBuilder builder = new StringBuilder();
		for (String word : words) {
			for (int i = word.length() - 1; i >= 0; i--) {
				builder.append(word.charAt(i));
			}
			builder.append(" ");
		}
		return builder.toString().trim();
	}
}
