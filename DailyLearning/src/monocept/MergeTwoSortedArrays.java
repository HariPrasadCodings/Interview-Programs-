package monocept;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MergeTwoSortedArrays {
	public static void main(String[] args) {
		int[] a = { 1, 5, 8, 13 };
		int[] b = { 2, 4, 11, 76 };

		// approach: 1 easiest way
		List<Integer> merge = Stream.concat(Arrays.stream(a).boxed(), Arrays.stream(b).boxed()).sorted()
				.collect(Collectors.toList());
		System.out.println(merge);

		// approach : 2 using while loop
		int[] mergeArray = mergeArray(a, b);
		System.out.println(Arrays.toString(mergeArray));

		// approach : 3 using stream api
		int[] array = Arrays.stream(new int[][] { a, b }).flatMapToInt(Arrays::stream).sorted().toArray();
		System.out.println(Arrays.toString(array));
	}

	public static int[] mergeArray(int[] a, int[] b) {
		int[] result = new int[a.length + b.length];
		int i = 0;
		int j = 0;
		int k = 0;

		while (i < a.length && j < b.length) {
			if (a[i] < b[j]) {
				result[k++] = a[i++];
			} else {
				result[k++] = b[j++];
			}
		}
		while (i < a.length) {
			result[k++] = a[i++];
		}
		while (j < b.length) {
			result[k++] = b[j++];
		}
		return result;
	}

}
