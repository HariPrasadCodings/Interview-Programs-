package test;

public class Test2 {

	static {
		System.out.println("Static Block 1");
	}
	{
		System.out.println("Instance Block 1");
	}
	public Test2() {
		System.out.println("Constructor");
	}
	static {
		System.out.println("Static Block 2");
	}
	{
		System.out.println("Instance Block 2");
	}
	public static void display() {
		System.out.println("Static Method Display");
	}
	public static void main(String[] args) {
		Test2.display();
		Test2 t1 = new Test2();
		Test2 t2 = new Test2();
	}

}
