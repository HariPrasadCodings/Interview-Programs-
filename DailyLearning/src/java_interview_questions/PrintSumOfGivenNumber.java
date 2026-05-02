package java_interview_questions;

public class PrintSumOfGivenNumber {
	public static void main(String[] args) {
		int n = 123456;

		int sum = 0;

		while (n != 0) {
			int k = n % 10;
			sum = k + sum;
			n = n / 10;
		}
		System.out.println(sum);
	}

}
