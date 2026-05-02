package basics;

import java.util.Arrays;
import java.util.stream.Stream;

public class MergeTwoUnsortedArray {
	public static void main(String[] args) {
		int[] arr1 = {1, 5, 3};
		int[] arr2 = {4, 6, 2};
		System.out.println(Arrays.toString(mergeTwoSortedArrays(arr1, arr2)));
	}

	private static int[] mergeTwoSortedArrays(int[] arr1, int[] arr2) {
		return Stream
				.concat(Arrays.stream(arr1).boxed(),
						Arrays.stream(arr2).boxed())
				.sorted().mapToInt(Integer::intValue).toArray();
	}
}
