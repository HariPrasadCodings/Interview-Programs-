package java8;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SeparateOddAndEven {
	public static void main(String[] args) {
		int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

		List<Integer> list = Arrays.stream(arr).boxed().toList();
		Map<Boolean, List<Integer>> oddEven = list.stream().collect(Collectors.partitioningBy(i -> i % 2 == 0));
		List<Integer> even = oddEven.getOrDefault(true, list);
		List<Integer> odd = oddEven.getOrDefault(false, list);
		System.out.println("Even: " + even + "   " + "Odd " + odd);
	}

}
