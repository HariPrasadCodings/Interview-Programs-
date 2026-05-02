package java8features;

import java.util.Arrays;
import java.util.stream.IntStream;

public class MergeTwoUnsortedArrays {
	public static void main(String[] args) {
		int[] arr1 = { 1, 2, 3, 4 };
		int[] arr2 = { 7, 8, 5, 6, 9 };

		int[] concat = IntStream.concat(Arrays.stream(arr1), Arrays.stream(arr2)).sorted().toArray();
		System.out.println(Arrays.toString(concat));

	}

}
