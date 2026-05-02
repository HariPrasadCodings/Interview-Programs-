package practise;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

public class SecondLargestNumber {
	public static void main(String[] args) {
		int[] arr = { 2, 3, 4, 12, 54, 35, 67, 20, 32 };
		Optional<Integer> secondLargest = Arrays.stream(arr).boxed().sorted(Comparator.reverseOrder()).skip(1)
				.findFirst();
		secondLargest.ifPresent(System.out::print);

	}

}
