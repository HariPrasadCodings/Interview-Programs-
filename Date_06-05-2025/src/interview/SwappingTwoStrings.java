package interview;

public class SwappingTwoStrings {
	public static void main(String[] args) {
		String a = "Hari";
		String b = "Prasad";

		a = a + b;
		b = a.substring(0, a.length() - b.length());
		a = a.substring(b.length());
		System.out.println("Strings after swap: a = " + a + " and b = " + b);
	}

}
