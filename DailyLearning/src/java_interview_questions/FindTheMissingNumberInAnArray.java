package java_interview_questions;

public class FindTheMissingNumberInAnArray {
	public static void main(String[] args) {
		int[] arr = { 1, 2, 3, 4, 6, 7, 8, 9 };
		int sum = (9 * 10) / 2;
		int newSum = 0;
		for (int i = 0; i < arr.length; i++) {
			newSum = newSum + arr[i];
		}
		System.out.println("Missing Number in the array is : " + (sum - newSum));
	}

}
