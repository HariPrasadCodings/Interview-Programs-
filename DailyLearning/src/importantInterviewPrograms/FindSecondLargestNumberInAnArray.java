package importantInterviewPrograms;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class FindSecondLargestNumberInAnArray {
	public static void main(String[] args) {
		int[] arr = {3, 4, 5, 6, 7, 8};

		// approach:1
		int secondHighest = Arrays.stream(arr).boxed()
				.sorted(Comparator.reverseOrder()).skip(1).findFirst().get();
		System.out.println(secondHighest);

		// approach:2
		PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(
				Collections.reverseOrder());
		for (int num : arr) {
			priorityQueue.add(num);
		}
		int highest = priorityQueue.poll();
		int Highest = priorityQueue.poll();
		System.out.println("Second Highest: " + Highest);

		// approach:3
		int orElseThrow = Arrays.stream(arr).distinct().sorted()
				.skip(arr.length - 2).findFirst()
				.orElseThrow(() -> new RuntimeException(
						"No second highest element"));
		System.out.println(orElseThrow);

	}

}
