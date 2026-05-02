package interview;

import java.util.Arrays;

public class SumAndAverageElementsOfAnArray {
	public static void main(String[] args) {
		int[] arr1 = { 2, 3, 11, 4, 9, 13, 5 };

		// sum
		int sum = Arrays.stream(arr1).sum();
		// average
		double average = Arrays.stream(arr1).average().getAsDouble();
		System.out.println("Sum: " + sum + " Average: " + average);
	}

}
