package date_04_07_2025;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class MergeSortedLists {
	public static void main(String[] args) {
		List<Integer> list1 = Arrays.asList(1, 3, 5,4);
		List<Integer> list2 = Arrays.asList(2, 4, 6);

		List<Integer> mergedList = Stream.concat(list1.stream(), list2.stream())
				.distinct().sorted().toList();
		System.out.println(mergedList);
	}

}
