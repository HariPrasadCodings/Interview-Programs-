package java_interview_questions;

public class FindSumOfEvenAndOdd {
	public static void main(String[] args) {
		int n = 123456;
		int evenSum = 0;
		int oddSum = 0;

		while (n != 0) {
			int digit = n % 10;
			if (digit % 2 == 0) {
				evenSum += digit; // 2 + 4 + 6 = 12
			} else {
				oddSum += digit; // 1 + 3 + 5 = 9
			}
			n = n / 10;
		}
		System.out.println(evenSum + " : " + oddSum);
	}

}
