package java8features;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MaxAndMinElemnstInList {
	public static void main(String[] args) {
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

		int max = numbers.stream().max(Comparator.naturalOrder()).get();
		int min = numbers.stream().min(Comparator.naturalOrder()).get();
		System.out.println("Max: " + max + " and Min: " + min);
	}

}
