package practise;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MergeTwoUnSortedArrays {
	public static void main(String[] args) {
		int[] a = { 1, 2, 5, 6, 3, 4, 7 };

		int[] b = { 8, 2, 9, 6, 12, 11, 7 };

		Object[] merge = Stream.concat(Arrays.stream(a).boxed(), Arrays.stream(b).boxed()).toArray();
		int[] result = IntStream.concat(Arrays.stream(a), Arrays.stream(b)).toArray();
		System.out.println(Arrays.toString(result));
		System.out.println(Arrays.toString(merge));
	}

}
