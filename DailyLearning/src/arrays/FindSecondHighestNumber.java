package arrays;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

public class FindSecondHighestNumber {
	public static void main(String[] args) {
		int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		Optional<Integer> secondHighest = Arrays.stream(arr).boxed().distinct().sorted(Comparator.reverseOrder())
				.skip(1).findFirst();
		if (secondHighest.isPresent()) {
			System.out.println("Second Highest element is: " + secondHighest.get());
		} else {
			System.out.println("Second Highest element not present");
		}

	}

}
