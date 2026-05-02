package interview;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortTheListInReverseOrder {
	public static void main(String[] args) {

		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
		List<Integer> reverseOrder = list.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
		System.out.println(reverseOrder);
	}

}
