package practise;

import java.util.Arrays;
import java.util.stream.IntStream;

public class ReverseArray {
	public static void main(String[] args) {
		int[] arr = { 1, 2, 3, 4, 5, 6 };
		int[] reverse = IntStream.rangeClosed(1, arr.length).map(i -> arr[arr.length - i]).toArray();
		System.out.println(Arrays.toString(reverse));
	}

}
