package interview;

import java.util.Arrays;
import java.util.Comparator;

public class SecondLargestNumberInAnArray {
	public static void main(String[] args) {
		int[] arr1 = { 2, 3, 1, 4, 9, 3, 5 };

		// second largest
		Arrays.stream(arr1).boxed().sorted(Comparator.reverseOrder()).skip(1).findFirst()
				.ifPresent(num -> System.out.println(num));

		// second lowest
		Arrays.stream(arr1).sorted().skip(1).findFirst().ifPresent(num -> System.out.println(num));
	}

}
