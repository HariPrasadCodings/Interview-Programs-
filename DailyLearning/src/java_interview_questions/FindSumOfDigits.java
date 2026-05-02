package java_interview_questions;

public class FindSumOfDigits {
	public static void main(String[] args) {
		int n = 1234;
		int sum = 0;

		while (n > 0) {
			int digit = n % 10;
			sum = sum + digit;
			n = n / 10;
		}
		System.out.println("Sum of digits: " + sum);
	}

}
