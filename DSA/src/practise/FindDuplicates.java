package practise;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class FindDuplicates {
	public static void main(String[] args) {
		int[] arr = { 1, 2, 3, 4, 2, 1, 6, 5, 2 };

		Set<Integer> set = new LinkedHashSet<>();
		List<Integer> duplicates = Arrays.stream(arr).boxed().filter(n -> !set.add(n)).distinct().toList();
		System.out.println(duplicates);

		int[] result = findDuplicates(arr);
		System.out.println(Arrays.toString(result));
	}

	private static int[] findDuplicates(int[] arr) {
		Set<Integer> set = new HashSet<>();
		Set<Integer> duplicates = new HashSet<>();

		for (int num : arr) {
			if (!set.add(num)) {
				duplicates.add(num);
			}
		}

		int[] result = new int[duplicates.size()];
		int i = 0;
		for (int num : duplicates) {
			result[i++] = num;
		}

		return result;

	}
}
