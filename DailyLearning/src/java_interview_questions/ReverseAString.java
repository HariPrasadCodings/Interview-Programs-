package java_interview_questions;

public class ReverseAString {
	public static void main(String[] args) {
		String input = "ABCD";

		// approach:1
		String reversed = new StringBuilder(input).reverse().toString();
		System.out.println(reversed);

		// approach: 2
		String reverse = "";
		for (int i = input.length() - 1; i >= 0; i--) {
			reverse = reverse + input.charAt(i);
		}
		System.out.println(reverse);

		// approach :3
		StringBuilder builder = new StringBuilder();
		for (int i = input.length() - 1; i >= 0; i--) {
			builder.append(input.charAt(i));
		}
		System.out.println(builder.toString());
	}

}
