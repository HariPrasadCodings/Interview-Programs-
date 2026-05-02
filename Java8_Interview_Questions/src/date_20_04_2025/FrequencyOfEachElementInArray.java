package date_20_04_2025;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FrequencyOfEachElementInArray {
	public static void main(String[] args) {
		int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 3, 5, 2, 6};
		Map<Integer, Integer> frequency = new HashMap<>();

		for (int num : arr) {
			frequency.put(num, frequency.getOrDefault(num, 0) + 1);
		}
		System.out.println("Frequency: " + frequency);

		// System.out.println("Frequency of each element: ");
		// for (Map.Entry<Integer, Integer> entry : frequency.entrySet()) {
		// System.out.println(entry.getKey() + " - " + entry.getValue());
		// }

		List<Integer> list = List.of(1, 2, 3, 4, 5, 3, 2, 1, 0, 6, 7);
		Map<Integer, Long> frequecnyOfEach = list.stream()
				.collect(Collectors.groupingBy(Function.identity(),
						LinkedHashMap::new, Collectors.counting()));
		System.out.println(frequecnyOfEach);
	}

}
