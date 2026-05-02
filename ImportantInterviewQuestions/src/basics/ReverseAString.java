package basics;

public class ReverseAString {
	public static void main(String[] args) {
		String original = "ABCD";

		String reversed = new StringBuilder(original).reverse().toString();
		System.out.println(reversed);

		String reverse = "";

		// Using charAt() in a Loop

		for (int i = original.length() - 1; i >= 0; i--) {
			reverse = reverse + original.charAt(i);
		}
		System.out.println(reverse);
	}

}
