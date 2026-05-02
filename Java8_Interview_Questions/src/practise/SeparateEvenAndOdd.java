package practise;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SeparateEvenAndOdd {
	public static void main(String[] args) {
		List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
		Map<Boolean, List<Integer>> partition = numbers.stream().collect(Collectors.partitioningBy(i -> i % 2 != 0));

		// Using concat approach
		List<Integer> separate = Stream
				.concat(numbers.stream().filter(n -> n % 2 == 0), numbers.stream().filter(n -> n % 2 != 0)).toList();
		System.out.println(separate);

		System.out.println(partition);
	}

}
