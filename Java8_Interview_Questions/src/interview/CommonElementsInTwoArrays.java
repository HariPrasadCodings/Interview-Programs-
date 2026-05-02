package interview;

import java.util.Arrays;
import java.util.List;

public class CommonElementsInTwoArrays {
	public static void main(String[] args) {
		int[] arr1 = { 2, 3, 11, 4, 9, 13, 5 };
		int[] arr2 = { 10, 13, 1, 14, 9, 23, 6 };

		List<Integer> list1 = Arrays.stream(arr1).boxed().toList();
		List<Integer> list2 = Arrays.stream(arr2).boxed().toList();

		list1.stream().filter(list2::contains).forEach(System.out::println);
	}

}
