package strings;

/**
 * Q #1) Write a Java Program to reverse a string without using String inbuilt
 * function.
 */

public class StringReverse {
	public static void main(String[] args) {
		String s = "akruthi";

		String reverse = reverse(s);

		System.out.println("Reversed String: " + reverse);
	}

	/**
	 * Explanation: This solution manually swaps the characters of the string from
	 * the start and end, moving towards the center, effectively reversing the
	 * string without using any built-in functions.
	 */
	private static String reverse(String input) {
		char[] ch = input.toCharArray(); // [a, k, r, u, t, h, i]

		int left = 0;
		int right = input.length() - 1;

		while (left < right) {
			char temp = ch[left];
			ch[left] = ch[right];
			ch[right] = temp;

			left++;
			right--;

		}

		return new String(ch);
	}

}
