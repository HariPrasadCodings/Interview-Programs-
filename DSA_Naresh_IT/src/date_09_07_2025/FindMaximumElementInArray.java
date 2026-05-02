package date_09_07_2025;

import java.util.Arrays;

public class FindMaximumElementInArray {
	public static void main(String[] args) {
		int[] arr = { 1, 2, 3, 4, 5, 6, 7 };

		System.out.println("Maximum element is: " + findMaximum(arr));

		System.out.println("Maximum element is: " + sortingUsingBuiltInMethod(arr));

	}

	// sorting approach
	static void sorting(int[] arr) {

	}

	// TC: O(N) : SC: O(1) This is Linear scan approach
	static int findMaximum(int[] arr) {
		int max = arr[0];

		for (int num : arr) {
			if (num > max) {
				max = num;
			}
		}

		return max;
	}

	// In built approach
	static int sortingUsingBuiltInMethod(int[] arr) {
		Arrays.sort(arr);

		return arr[arr.length - 1];
	}

}
