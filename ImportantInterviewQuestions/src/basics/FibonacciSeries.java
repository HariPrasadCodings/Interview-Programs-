package basics;

public class FibonacciSeries {

	public static void main(String[] args) {
		int terms = 10;
		int first = 0;
		int second = 1;

		System.out.println("Fibonacci series for " + terms + " terms");

		for (int i = 0; i <= terms; i++) {
			System.out.print(first + " ");

			int next = first + second;
			first = second;
			second = next;
		}

	}
}
