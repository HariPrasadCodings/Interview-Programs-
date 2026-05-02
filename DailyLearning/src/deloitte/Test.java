package deloitte;

import java.io.FileNotFoundException;

class A {
	A() {

	}
	public void m1() throws FileNotFoundException {
		System.out.println("m1");
	}
}

class B extends A {

	public void m1() throws FileNotFoundException {
		System.out.println("B");
	}
}

public class Test {
	public static void main(String[] args) throws FileNotFoundException {
		A a = new B();
		a.m1();
		System.out.println(10 + 20 + "Java");
		System.out.println("Java" + 20 + " Programming" + 10);
	}

}
