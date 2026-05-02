package interview;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SeparateEvenAndOdd {
	public static void main(String[] args) {
		int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };

		Map<Boolean, List<Integer>> partitioned = Arrays.stream(arr).boxed()
				.collect(Collectors.partitioningBy(i -> i % 2 == 0));
		List<Integer> evenNumbers = partitioned.get(true);
		List<Integer> oddNumbers = partitioned.get(false);
		for (int result : evenNumbers) {
			System.out.print(result + " ");
		}
		for (int result : oddNumbers) {
			System.out.print(result + " ");
		}
		System.out.println();
		int[] evenNumber = Arrays.stream(arr).filter(i -> i % 2 == 0).toArray();
		int[] oddNumber = Arrays.stream(arr).filter(i -> i % 2 != 0).toArray();
		System.out.println(Arrays.toString(evenNumber) + " " + Arrays.toString(oddNumber));
	}

}
