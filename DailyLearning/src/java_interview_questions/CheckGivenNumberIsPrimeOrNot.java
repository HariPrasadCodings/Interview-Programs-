package java_interview_questions;

import java.util.Scanner;

public class CheckGivenNumberIsPrimeOrNot {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter any number: ");
		int num = scanner.nextInt();
		isPrimeNumber(num);
		if (isPrimeNumber(num)) {
			System.out.println(num + " is Prime number");
		} else {
			System.out.println(num + " is not a Prime number");
		}
		scanner.close();
	}

	private static boolean isPrimeNumber(int num) {
		if (num <= 1) {
			return false;
		} else {
			for (int i = 2; i <= Math.sqrt(num); i++) {
				if (num % i == 0) {
					return false;
				}
			}
		}
		return true;

	}
}
