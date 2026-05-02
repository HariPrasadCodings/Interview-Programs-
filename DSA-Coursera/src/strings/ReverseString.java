package strings;

public class ReverseString {
	public static void main(String[] args) {
		System.out.println("======Before reversing======");
		String s = "HariPrasad";
		System.out.println(s);
		String reversed = "";

		for (int i = s.length() - 1; i >= 0; i--) {
			reversed = reversed + s.charAt(i);
		}
		System.out.println("\n=====After reversing=======");
		System.out.println(reversed);

	}

}
