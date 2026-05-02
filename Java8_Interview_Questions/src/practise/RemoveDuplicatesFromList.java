package practise;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RemoveDuplicatesFromList {
	public static void main(String[] args) {
		List<Integer> numbers = List.of(1, 2, 3, 7, 8, 4, 5, 6, 7, 8, 9, 2, 5, 6);
		Set<Integer> seen = new HashSet<>();
		List<Integer> removingDuplicates = numbers.stream().filter(n -> !seen.add(n)).sorted().toList();

		List<Integer> distinct = numbers.stream().distinct().toList();
		System.out.println(removingDuplicates);

		System.out.println(distinct);
	}

}
