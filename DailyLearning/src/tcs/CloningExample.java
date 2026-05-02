package tcs;

interface A {
	int a = 10;

	public void m1();
}

class Person implements Cloneable {
	String name;
	int age;

	Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@Override
	public String toString() {
		return "Person{name='" + name + "', age=" + age + '}';
	}
}

public class CloningExample {
	public static void main(String[] args) {

		Person original = new Person("Harsha", 28);
		Person clone;
		try {
			clone = (Person) original.clone();
			clone.name = "Suresh";
			System.out.println("original: " + original);
			System.out.println("clone: " + clone);
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}

	}

}
