package date_04_07_2025;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class FindSecondHighestNumberInList {
	public static void main(String[] args) {
		List<Integer> numbers = Arrays.asList(5, 2, 8, 1, 9, 3);

		Optional<Integer> secondHighest = numbers.stream()
				.sorted(Comparator.reverseOrder()).skip(1).findFirst();
		if (secondHighest.isPresent()) {
			System.out.println(
					"Second Highest element is: " + secondHighest.get());
		} else {
			System.out.println("Second highest number not available");
		}

		// approach : 2
		Integer secondHighestElement = numbers.stream()
				.sorted((a, b) -> b.compareTo(a)).skip(1).findFirst()
				.orElse(-1);
		System.out.println(secondHighestElement);
	}

}
