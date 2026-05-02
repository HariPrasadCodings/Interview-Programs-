package thirtyPrograms;

public class ReverseAString {
	public static void main(String[] args) {
		String s = "ABCD";

		String reverse = new StringBuffer(s).reverse().toString();
		System.out.println(reverse);

		String rev = "";
		for (int i = s.length() - 1; i >= 0; i--) {
			rev = rev + s.charAt(i);
		}

		System.out.println(rev);

	}

}
