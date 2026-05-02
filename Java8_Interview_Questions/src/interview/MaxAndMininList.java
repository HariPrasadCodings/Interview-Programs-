package interview;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MaxAndMininList {
	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
		list.stream().sorted(Comparator.reverseOrder()).findFirst()
				.ifPresent(num -> System.out.println("Max Element is: " + num));
		list.stream().sorted().findFirst().ifPresent(num -> System.out.println("Min Element is: " + num));

		// other approach
		Integer max = list.stream().max(Comparator.naturalOrder()).get();
		Integer min = list.stream().min(Comparator.naturalOrder()).get();
		System.out.println(max + " " + min);
	}

}
