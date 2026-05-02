package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class FindUniqueElementsInArray {
	public static void main(String[] args) {
		int[] nums = { 2, 4, 5, 6, 1, 2, 3, 4, 5, 6, 9 };

		System.out.println("Unique elements are: " + findUniqueElements(nums));

		// Approach: 2
		int[] unique = Arrays.stream(nums).distinct().sorted().toArray();
		System.out.println(Arrays.toString(unique));
	}

	static List<Integer> findUniqueElements(int[] arr) {
		Set<Integer> set = new TreeSet<>();
		for (int num : arr) {
			set.add(num);
		}
		return new ArrayList<>(set);
	}

}
