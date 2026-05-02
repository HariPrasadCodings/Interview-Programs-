package date_20_04_2025;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SeparateOdd_Even_Numbers {
	public static void main(String[] args) {
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		List<Integer> evenOdd = Stream
				.concat(numbers.stream().filter(e -> e % 2 == 0),
						numbers.stream().filter(o -> o % 2 != 0))
				.toList();
		System.out.println(evenOdd);
		// Approach : 2
		Map<Boolean, List<Integer>> oddAndEven = numbers.stream()
				.collect(Collectors.partitioningBy(n -> n % 2 == 0));
		System.out.println(oddAndEven);
	}

}
