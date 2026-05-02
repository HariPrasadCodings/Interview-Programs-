package java_interview_questions;

public class CountEvenAndOddNumbers {
	public static void main(String[] args) {
		int n = 1234;
		int even = 0;
		int odd = 0;
		while (n > 0) {
			int digit = n % 10;
			if (digit % 2 == 0) {
				even++;
			} else {
				odd++;
			}
			n = n / 10;
		}
		System.out.println("Even Numbers: " + even);
		System.out.println("Odd Numbers: " + odd);
	}

}
