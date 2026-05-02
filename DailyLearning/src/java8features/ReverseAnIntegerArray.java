package java8features;

import java.util.Arrays;
import java.util.stream.IntStream;

public class ReverseAnIntegerArray {
	public static void main(String[] args) {
		int[] arr = { 1, 2, 3, 4, 5, 6 };
		int[] arrayReversed = IntStream.range(1, arr.length).map(i -> arr[arr.length - i]).toArray();
		System.out.println(Arrays.toString(arrayReversed));
	}

}
