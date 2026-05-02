package importantInterviewPrograms;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class FindDuplicateNumberInAnArray {
	public static void main(String[] args) {
		int[] arr = {3, 4, 5, 6, 6, 7, 7};

		// approach:1
		Map<Integer, Integer> valueCount = new HashMap<>();

		for (int count : arr) {
			valueCount.put(count, valueCount.getOrDefault(count, 0) + 1);
		}
		for (Map.Entry<Integer, Integer> entrySet : valueCount.entrySet()) {
			if (entrySet.getValue() > 1) {

				System.out.println("Duplicate value: " + entrySet.getKey());
			}
		}

		// approach: 2
		Set<Integer> set = new HashSet<>();
		Set<Integer> collect = Arrays.stream(arr).boxed()
				.filter(n -> !set.add(n)).collect(Collectors.toSet());
		System.out.println(collect);

		// approach: 3
		Set<Integer> duplicates = new HashSet<>();
		Set<Integer> seen = new HashSet<>();
		for (int i = 0; i < arr.length; i++) {
			if (!seen.add(arr[i])) {
				duplicates.add(arr[i]);
			}
		}
		System.out.println("Duplicate: " + duplicates);

	}

}
