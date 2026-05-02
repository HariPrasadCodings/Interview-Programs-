package strings;

public class ReverseStringWithoutUsingInBuildMethods {
	public static void main(String[] args) {
		String s = "hariprasad";
		System.out.println("Reversed String: " + reverse(s));
	}

	public static String reverse(String s) {
		char[] ch = s.toCharArray();
		int left = 0;
		int right = s.length() - 1;

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
