package interview.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class PeekDemo {
	public static void main(String[] args) {
		final List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));

		List<Integer> peek = list.stream().filter(n -> n % 2 != 0)
				.peek(value -> System.out.println("Filtered Value: " + value)).map(n -> n * 10).toList();
		System.out.println(peek);

		Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5);

		stream.parallel().forEach(System.out::println);

		list.parallelStream().forEach(System.out::println);

	}

}
