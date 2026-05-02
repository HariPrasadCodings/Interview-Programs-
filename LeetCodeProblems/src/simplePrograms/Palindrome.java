package simplePrograms;

public class Palindrome {

	public static boolean isPalindrome(String s) {
		s = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
		System.out.println(s);
		String string = new StringBuilder(s).reverse().toString();
		if (s.equals(string))
			return true;
		else {
			return false;
		}
	}

	public static void main(String[] args) {
		String s = "A man, a plan, a canal: Panama";
		System.out.println(isPalindrome(s));

	}

}
