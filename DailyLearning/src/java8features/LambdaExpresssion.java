package java8features;

interface Inter {
	int add(int a, int b);
}

public class LambdaExpresssion {
	public static void main(String[] args) {
		Inter expresssion = new Inter() {
			@Override
			public int add(int a, int b) {
				return a + b;

			}
		};

		int result = expresssion.add(10, 20);
		System.out.println(result);

		Inter express = (a, b) -> a + b;
		System.out.println(express.add(20, 20));

	}

}
