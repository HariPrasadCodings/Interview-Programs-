package arrays;

import java.util.List;
import java.util.stream.Stream;

public class MoveZerosAtEnd {

	public static void main(String[] args) {
		List<Integer> numbers = List.of(1, 0, -3, 0, 5, -2, 0, 8, 0, -1);
		List<Integer> moveZerosAtEnd = Stream
				.concat(numbers.stream().filter(x -> x != 0), numbers.stream().filter(x -> x == 0)).toList();

		System.out.println(moveZerosAtEnd);
	}
}
