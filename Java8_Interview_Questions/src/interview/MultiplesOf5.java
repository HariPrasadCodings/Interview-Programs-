package interview;

import java.util.Arrays;
import java.util.List;

public class MultiplesOf5 {
	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
		list.stream().filter(i -> i % 5 == 0).toList()
				.forEach(System.out::println);
	}

}
