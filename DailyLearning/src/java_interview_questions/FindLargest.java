package java_interview_questions;

public class FindLargest {
	public static void main(String[] args) {
		int a = 10;
		int b = 20;
		int c = 30;
		if (a > b && a > c) {
			System.out.println("Largest Number: " + a);
		} else if (b > a && b > c) {
			System.out.println("Largest Number: " + b);
		} else {
			System.out.println("Largest Number: " + c);
		}
	}

}
