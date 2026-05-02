package test;

public class Sample {

	public static void main(String[] args) {
		new Sample("Java");
		System.out.println();
		int i = 1;

		// do-while loop demo
		do {
			System.out.print(i + "  ");
			i++;
		} while (i <= 5);
		System.out.println();
		// while loop demo
		int a = 0;
		while (a < 5) {
			a++;
			if (a == 2) {
				continue;
			}
			System.out.println(a);
		}
	}

	Sample() {
		System.out.print("Hello ");
	}
	Sample(String str) {
		this();
		System.out.print("World ");
	}

}
