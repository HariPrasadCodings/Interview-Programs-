package basics;

import java.util.Arrays;
import java.util.Comparator;

public class FindMaxAndMinElementsInArray {
	public static void main(String[] args) {
		int[] arr = {2, 3, 4, 8, 7, 6, 9, 1};

		int min = Arrays.stream(arr).sorted().findFirst().getAsInt();
		System.out.println(min);

		int max = Arrays.stream(arr).boxed().sorted(Comparator.reverseOrder())
				.findFirst().get();
		System.out.println(max);
		System.out.println(findMaxElement(arr));
	}

	public static int findMaxElement(int[] arr) {

		if (arr.length <= 0)
			return 0;
		int max = arr[0];

		for (int num : arr) {
			if (num > arr[0]) {
				max = num;
			}
		}
		return max;
	}
}
