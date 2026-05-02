package arrays;

public class StringManipulationExample {
	public static void main(String[] args) {
		// String literal
		String s1 = "Hello";
		System.out.println(s1);

		// String Object
		String s2 = new String("World");
		System.out.println(s2);

		// Concatenating String Literal and String Object
		String s3 = s1 + s2;
		System.out.println(s3);
		System.out.println();
		// String methods
		System.out.println(s3.length());
		System.out.println(s3.charAt(0));
		System.out.println(s1.concat(s2));
		System.out.println(s3.substring(0, 5));
		System.out.println(s1.equals(s2));
		System.out.println(s3.contains("Hello"));
		System.out.println(s3.toLowerCase());
		System.out.println(s3.toUpperCase());
		System.out.println(s3.trim());
	}

}
