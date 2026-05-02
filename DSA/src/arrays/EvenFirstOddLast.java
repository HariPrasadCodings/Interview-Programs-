package arrays;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class EvenFirstOddLast {
	public static void main(String[] args) {
		int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8 };

		int[] result = evenFirstOddLast(arr);
		System.out.println(Arrays.toString(result));

		// Approach: 2
		List<Integer> list = Stream.concat(Arrays.stream(arr).boxed().filter(n -> n % 2 == 0),
				Arrays.stream(arr).boxed().filter(n -> n % 2 != 0)).toList();
		System.out.println(list);
	}

	static int[] evenFirstOddLast(int[] arr) {
		int[] result = new int[arr.length];

		int index = 0;

		for (int i = 0; i < arr.length; i++) {
			if (arr[i] % 2 == 0) {
				result[index++] = arr[i];
			}
		}

		for (int i = 0; i < arr.length; i++) {
			if (arr[i] % 2 != 0) {
				result[index++] = arr[i];
			}
		}

		return result;
	}

}
