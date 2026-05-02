package strings;

public class ReverseVowelsOfString {
	public static void main(String[] args) {
		String s = "IceCreAm";
		System.out.println("Reversed String: " + reverseVowels(s));
	}

	private static String reverseVowels(String s) {
		char[] chars = s.toCharArray();
		String vowels = "aeiouAEIOU";
		int left = 0;
		int right = s.length() - 1;

		while (left < right) {
			while (left < right && vowels.indexOf(chars[left]) == -1) {
				left++;
			}
			while (left < right && vowels.indexOf(chars[right]) == -1) {
				right--;
			}
			char temp = chars[left];
			chars[left] = chars[right];
			chars[right] = temp;

			left++;
			right--;
		}
		return new String(chars);
	}

}
