package interview_practise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RemoveDuplicates {
	public static void main(String[] args) {
		int[] nums = { 1, 2, 3, 1, 1, 4, 5, 2 };
		Object[] distinct = Arrays.stream(nums).boxed().distinct().toArray();
		System.out.println(Arrays.toString(distinct));

		System.out.println(removeDuplicates(nums));

	}

	private static List<Integer> removeDuplicates(int[] nums) {
		Set<Integer> set = new HashSet<>();
		List<Integer> list = new ArrayList<>();

		for (int num : nums) {
			if (set.add(num)) {
				list.add(num);
			}
		}
		return list;
	}

}
