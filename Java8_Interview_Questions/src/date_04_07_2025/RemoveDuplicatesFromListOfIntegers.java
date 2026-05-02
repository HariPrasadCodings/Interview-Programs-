package date_04_07_2025;

import java.util.Arrays;
import java.util.List;

public class RemoveDuplicatesFromListOfIntegers {
	public static void main(String[] args) {
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 2, 5, 6, 8, 1, 5);
		numbers.stream().distinct()
				.forEach(result -> System.out.println(result));
	}

}
