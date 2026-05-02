package interview;

import java.util.Arrays;

public class ParallerSort {
	public static void main(String[] args) {
		int[] numbers = {5, 3, 8, 1, 9, 4, 7, 6, 2, 0};

		System.out.println("Before Sorting: " + Arrays.toString(numbers));

		Arrays.parallelSort(numbers);

		System.out.println("After Sorting: " + Arrays.toString(numbers));

	}

}
