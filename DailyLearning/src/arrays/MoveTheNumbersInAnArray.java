package arrays;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MoveTheNumbersInAnArray {
	public static void main(String[] args) {
		int[] arr = { -3, 2, 0, -1, -5, 0, 4, 0, 5, 0 };
		// In the above array move all the 0's right side and non -zero elements left
		// side without changing the order of them Use only java8 streams
		Object[] orderedElements = Stream
				.concat(Arrays.stream(arr).boxed().filter(n -> n != 0), Arrays.stream(arr).boxed().filter(n -> n == 0))
				.toArray();
		System.out.println(Arrays.toString(orderedElements));

		// output: [-3, 2, -1, -5, 4, 5, 0, 0, 0, 0]

		int[] arr2 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

		// Write java 8 code to separate odd nubers left side and even numbers right
		// side

		// step : 1 Convert the arrays into list
		List<Integer> list = Arrays.stream(arr2).boxed().toList();

		// step: 2 Use the Stream. concat method to separate the even and odd numbers
		List<Integer> oddEvenSeparate = Stream
				.concat(list.stream().filter(e -> e % 2 != 0), list.stream().filter(o -> o % 2 == 0))
				.collect(Collectors.toList());
		System.out.println(oddEvenSeparate);
		
		// [1, 3, 5, 7, 9, 2, 4, 6, 8, 10]

	}

}
