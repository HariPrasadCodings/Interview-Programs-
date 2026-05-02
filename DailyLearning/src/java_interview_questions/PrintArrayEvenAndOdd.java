package java_interview_questions;

public class PrintArrayEvenAndOdd {
	public static void main(String[] args) {
		int[] arr = {1, 2, 3, 4};
		System.out.println("Even Numbers: ");
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] % 2 == 0) {
				System.out.println(arr[i]);
			}
		}
		System.out.println("Odd Numbers: ");
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] % 2 != 0) {
				System.out.println(arr[i]);
			}
		}

	}

}
