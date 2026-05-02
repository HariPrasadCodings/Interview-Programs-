package arrays;

import java.util.LinkedHashMap;
import java.util.Map;

public class PrintEachNumberCount {
	public static void main(String[] args) {
		int[] arr = { 1, 2, 3, 4, 5, 6, 3, 2, 4, 7, 1 };
		Map<Integer, Integer> count = new LinkedHashMap<>();
		for (int values : arr) {
			count.put(values, count.getOrDefault(values, 0) + 1);
		}
		System.out.println(count);
	}

}
