package java_interview_questions;

public class CountNumberOfDigits {
	public static void main(String[] args) {
		int n = 1234;

		int count = 0;
		while (n > 0) {
			count++;
			n = n / 10;
		}
		System.out.println("Number Of Digits: " + count);
	}

}
