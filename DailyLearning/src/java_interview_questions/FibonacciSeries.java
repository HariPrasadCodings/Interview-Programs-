package java_interview_questions;

public class FibonacciSeries {
	public static void main(String[] args) {
		int first = 0;
		int second = 1;
		int total = 10;

		System.out.println("Fibonacci series till " + total + " terms:");

		for (int i = 1; i <= total; i++) {
			System.out.print(first + " ");
			int next = first + second;
			first = second;
			second = next;
		}

	}

}
