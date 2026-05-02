package interview;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MergeTwoUnSortedArrays {
	public static void main(String[] args) {
		int[] arr1 = {2, 3, 1, 4, 9, 3, 5};
		int[] arr2 = {7, 10, 1, 6, 9, 3, 5};

		int[] merge = IntStream.concat(Arrays.stream(arr1), Arrays.stream(arr2))
				.sorted().distinct().toArray();
		System.out.println(Arrays.toString(merge));

		// Approach : 2
		List<Integer> mergedList = Stream
				.concat(Arrays.stream(arr1).boxed(),
						Arrays.stream(arr2).boxed())
				.distinct().sorted().toList();
		System.out.println(mergedList);
	}

}
