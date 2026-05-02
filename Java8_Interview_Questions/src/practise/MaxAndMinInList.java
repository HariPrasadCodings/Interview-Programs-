package practise;

import java.util.Comparator;
import java.util.List;

public class MaxAndMinInList {
	public static void main(String[] args) {
		List<Integer> numbers = List.of(1, 2, 3, 4, 15, 25, 67, 85, 29, 30);

		// int max = numbers.stream().max(Comparator.naturalOrder()).get();
		int max = numbers.stream().sorted(Comparator.reverseOrder()).findFirst().get();
		System.out.println(max);

		// int min = numbers.stream().min(Comparator.naturalOrder()).get();
		int min = numbers.stream().sorted().findFirst().get();
		System.out.println(min);
	}

}
