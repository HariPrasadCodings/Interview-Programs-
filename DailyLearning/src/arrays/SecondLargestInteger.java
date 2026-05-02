package arrays;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

public class SecondLargestInteger {
	public static void main(String[] args) {
		int[] arr = { 1, 2, 4, 6, 3, 5 }; // 5
		int[] arr2 = { 10, 10, 10 };

		Optional<Integer> secondHighest = Arrays.stream(arr).boxed().sorted(Collections.reverseOrder()).skip(1)
				.findFirst();
		if (secondHighest.isPresent()) {
			System.out.println("Second Highest is: " + secondHighest.get());
		} else {
			System.out.println("Not Present..");
		}

		int second = Arrays.stream(arr2).boxed().sorted(Collections.reverseOrder()).distinct().skip(1).findFirst()
				.orElse(-1);
		System.out.println(second);
		
		
	}

}
