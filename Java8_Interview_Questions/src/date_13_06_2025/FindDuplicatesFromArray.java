package date_13_06_2025;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

public class FindDuplicatesFromArray {
	public static void main(String[] args) {
		int[] arr = {1, 2, 3, 4, 5, 3, 2, 1, 6, 7};

		List<Integer> list = Arrays.stream(arr).boxed().toList();
		Set<Integer> set = new HashSet<>();
		Set<Integer> collect = list.stream().filter(i -> !set.add(i))
				.collect(Collectors.toSet());
		System.out.println(collect);

		// using for each loop
		Set<Integer> seen = new HashSet<>();
		Set<Integer> duplicates = new HashSet<>();
		for (int num : arr) {
			if (!seen.add(num)) {
				duplicates.add(num);
			}
		}
		System.out.println("Duplicates: " + duplicates);

		// using a map approach
		Map<Integer, Integer> map = new HashMap<>();

		for (int nums : arr) {
			map.put(nums, map.getOrDefault(nums, 0) + 1);
		}
		for (Entry<Integer, Integer> entrySet : map.entrySet()) {
			if (entrySet.getValue() > 1) {
				System.out.print(entrySet.getKey() + " ");
			}
		}
		System.out.println();
		// Brute force approach
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[i] == arr[j]) {
					System.out.print(arr[i] + " ");
					break;
				}
			}
		}

	}

}
