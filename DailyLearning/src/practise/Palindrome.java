package practise;

public class Palindrome {
	public static void main(String[] args) {
		String s = "malayalam";

		String reversed = "";

		for (int i = s.length() - 1; i >= 0; i--) {
			reversed = reversed + s.charAt(i);
		}

		if (s.equals(reversed)) {
			System.out.println(s + " is Palindrome");
		} else {
			System.out.println(s + " is not palindrome");
		}

	}

}
