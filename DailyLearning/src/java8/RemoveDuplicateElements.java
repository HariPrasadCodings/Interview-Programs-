package java8;

import java.util.Arrays;
import java.util.List;

public class RemoveDuplicateElements {
	public static void main(String[] args) {
		List<Integer> nums = Arrays.asList(1, 3, 5, 6, 2, 3, 1, 6, 7);
		List<Integer> unique = nums.stream().distinct().toList();
		System.out.println(unique);
	}

}
