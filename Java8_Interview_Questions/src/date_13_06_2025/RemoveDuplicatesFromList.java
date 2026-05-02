package date_13_06_2025;

import java.util.Arrays;
import java.util.List;

public class RemoveDuplicatesFromList {
	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 2, 6,
				1);
		List<Integer> uniqueElements = list.stream().distinct().toList();
		System.out.println(uniqueElements);
	}

}
