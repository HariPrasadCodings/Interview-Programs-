package arrays;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Move all zeroes in an array to the end while maintaining the order of other
 * elements.
 */

public class MoveZerosToEnd {

	private static void moveZeroes(int[] arr) {
		int index = 0;

		for (int num : arr) {
			if (num != 0) {
				arr[index++] = num; // 1,2,3,12
			}
		}
		while (index < arr.length) {
			arr[index++] = 0;
		}
	}

	public static void main(String[] args) {
		int[] nums = {0, 1, 0, 2, 0, 3, 12};
		moveZeroes(nums);
		System.out.println(Arrays.toString(nums));
		System.out.println(Arrays.toString(moveZerosAtEnd(nums)));
	}

	// using stream api
	private static int[] moveZerosAtEnd(int[] arr) {
		List<Integer> nonZeros = Arrays.stream(arr).filter(n -> n != 0).boxed()
				.collect(Collectors.toList());

		while (nonZeros.size() < arr.length) {
			nonZeros.add(0);
		}

		return nonZeros.stream().mapToInt(Integer::intValue).toArray();
	}

}
