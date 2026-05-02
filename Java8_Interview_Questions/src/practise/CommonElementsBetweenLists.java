package practise;

import java.util.ArrayList;
import java.util.List;

public class CommonElementsBetweenLists {
	public static void main(String[] args) {
		List<Integer> list1 = List.of(1, 2, 3, 4, 5, 6);

		List<Integer> list2 = List.of(2, 7, 8, 9, 5, 10,1);

		list1.stream().filter(list2::contains).forEach(System.out::println);
		System.out.println();

		List<Integer> intersection = new ArrayList<>(list1);
		intersection.retainAll(list2);
		System.out.println(intersection);
	}

}
