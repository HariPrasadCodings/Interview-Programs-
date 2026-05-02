package test;

public class ReverseString {
	public static void main(String[] args) {
		String s = "sudheer";

//		for (int i = s.length() - 1; i >= 0; i--) {
//			System.out.print(s.charAt(i));
//		}

		StringBuffer buffer = new StringBuffer(s);
		String reversed = buffer.reverse().toString();
		System.out.println(reversed);

		// using recursive method
		String recursiveMethod = recursiveMethod(s);
		System.out.println(recursiveMethod);

	}

	private static String recursiveMethod(String s) {
		if (s == null || s.length() <= 1) {
			return s;
		}
		return recursiveMethod(s.substring(1)) + s.charAt(0);
	}

}
