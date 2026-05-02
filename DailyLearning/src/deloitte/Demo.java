package deloitte;

public abstract class Demo {
	static int a = 10;
	static int m1(int a, int b) {
		return a + b;
	}

	public static void main(String[] args) {
		Demo demo = new Demo() {

		};
		System.out.println(Demo.m1(10, 20));
	}

}
