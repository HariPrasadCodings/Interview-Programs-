package reversing;

public class ReverseString {
	public static void main(String[] args) {
		String s = "harsha";

		System.out.println(reverse(s));
	}

	static String reverse(String s) {
		if (s == null || s.length() <= 1) {
			return s;
		}

		char[] ch = s.toCharArray();

		int left = 0;
		int right = ch.length - 1;

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
