package test;

public class Test1 {
	public static void main(String[] args) {
		System.out.println("Returned Value: " + testMethod());

		Integer a = 1000;
		Integer b = 1000;
		Integer c = 10;
		Integer d = 10;
		System.out.println(a == b);
		System.out.println(c == d);
	}

	public static int testMethod() {
		try {
			return 10;
		} finally {
			System.out.println("Finally block executed");
		}
	}

}
