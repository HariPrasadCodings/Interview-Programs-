package interview;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Find2ndHighestNumber {
	public static void main(String[] args) {
		int[] arr = {1, 2, 3, 4, 5, 6, 7, 12, 0, 13};
		Optional<Integer> first = Arrays.stream(arr).boxed()
				.sorted(Comparator.reverseOrder()).skip(1).findFirst();
		first.ifPresent(result -> System.out.println(result));

		List<Integer> list = Arrays.stream(arr).boxed().toList();
		Integer integer = list.stream().sorted(Comparator.reverseOrder())
				.skip(1).findFirst().get();
		System.out.println(integer);

		// 2nd lowest
		Integer lowest = Arrays.stream(arr).boxed()
				.sorted(Comparator.naturalOrder()).skip(1).findFirst().get();
		System.out.println(lowest);
	}

}
