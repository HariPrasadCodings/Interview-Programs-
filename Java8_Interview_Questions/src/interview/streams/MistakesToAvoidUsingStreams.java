package interview.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class MistakesToAvoidUsingStreams {
	public static void main(String[] args) {

		// 1. Ignoring Optional when using findFirst() or findAny()

		List<String> names = Arrays.asList("hari", "obulesh", "ravi", "raju");

		// Wrong approach
		String string = names.stream().filter(name -> name.startsWith("r")).findFirst().get();
		System.out.println(string);

		// Right approach
		Optional<String> first = names.stream().filter(name -> name.startsWith("h")).findFirst();
		first.ifPresent(System.out::println);

		// 2. Modifying State of External Variables

		// Wrong approach
		List<Integer> numbers = new ArrayList<>();
		Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).filter(num -> num % 2 == 0).forEach(numbers::add);

		System.out.println(numbers);

		// Right approach Use collectors:
		List<Integer> list = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).filter(num -> num % 2 != 0).toList();
		System.out.println(list);

		// 3. Using Streams for Simple Iterations

		// Wrong approach
		List<String> fruits = Arrays.asList("apple", "banana", "orange", "mango");
		fruits.forEach(System.out::println);

		// Right approach Use streams only for tasks like filtering, mapping, or
		// reducing.

		List<Integer> nums = List.of(1, 2, 3, 4, 5);
		List<Integer> evenNumbers = nums.stream().filter(n -> n % 2 == 0).toList();
		System.out.println(evenNumbers); // Output: [2, 4]
	}

}
