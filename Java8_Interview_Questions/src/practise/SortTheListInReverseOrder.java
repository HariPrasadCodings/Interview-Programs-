package practise;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortTheListInReverseOrder {
	public static void main(String[] args) {
		List<Integer> nums = List.of(2, 3, 4, 5, 6, 7, 8, 9, 10);
		List<Integer> reversed = nums.stream().sorted(Comparator.reverseOrder()).toList();
		nums.stream().sorted(Collections.reverseOrder()).forEach(System.out::print);
		System.out.println(reversed);
	}

}
