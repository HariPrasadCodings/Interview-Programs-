package java_interview_questions;

import java.util.Arrays;
import java.util.stream.IntStream;

public class FindSumOfElementsInAnArray {
	public static void main(String[] args) {
		int[] arr = {1, 2, 3, 4};
		// approach: 1
		int sum = Arrays.stream(arr).sum();
		System.out.println("Sum: " + sum);

		// approach: 2
		int sums = 0;
		for (int i = 0; i < arr.length; i++) {
			sums = sums + arr[i];
		}
		System.out.println(sums);

		// approach:3
		int total = 0;
		for (int value : arr) {
			total = total + value;
		}
		System.out.println(total);

		// approach: 4
		int count = IntStream.of(arr).sum();
		System.out.println(count);
	}

}
