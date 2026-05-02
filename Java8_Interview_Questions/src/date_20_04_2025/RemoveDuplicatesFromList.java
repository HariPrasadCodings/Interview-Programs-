package date_20_04_2025;

import java.util.Arrays;
import java.util.List;

public class RemoveDuplicatesFromList {
	public static void main(String[] args) {
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 4,
				3, 6);
		List<Integer> non_Duplicates = numbers.stream().distinct().toList();
		System.out.println(non_Duplicates);
	}

}
