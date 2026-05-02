package interview;

import java.util.Arrays;
import java.util.stream.IntStream;

public class ReverseAnIntegerArray {
	public static void main(String[] args) {
		int[] arr1 = { 2, 3, 11, 4, 9, 13, 5 };

		int[] reversedArray = IntStream.rangeClosed(1, arr1.length).map(i -> arr1[arr1.length - i]).toArray();
		System.out.println(Arrays.toString(reversedArray));
	}

}
