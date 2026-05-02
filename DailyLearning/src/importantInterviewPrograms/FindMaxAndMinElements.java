package importantInterviewPrograms;

import java.util.Arrays;

public class FindMaxAndMinElements {
	public static void main(String[] args) {
		int[] arr = {3, 4, 5, 6};
		// approach:1
		int max = Arrays.stream(arr).max().getAsInt();
		System.out.println(max);

		int min = Arrays.stream(arr).min().getAsInt();
		System.out.println(min);

		// appraoch: 2
		int maxElement = arr[0];
		int minElement = arr[0];

		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > maxElement) {
				maxElement = arr[i];
			}
			if (arr[i] < minElement) {
				minElement = arr[i];
			}
		}
		System.out.println("Max Element: " + maxElement);
		System.out.println("Min Element: " + minElement);

		// approach:3
		Arrays.sort(arr);
		int minElement1 = arr[0];
		int maxElement2 = arr[arr.length - 1];
		System.out.println(minElement1);
		System.out.println(maxElement2);
	}
}
