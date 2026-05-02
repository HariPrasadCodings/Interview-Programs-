package deloitte_april_2026.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {
	public static void main(String[] args) {
		int[] arr = { 1, 2, 3, 4, 5, 6, 0, -1, -1, -2, -2, 3, -4, -6, -2 };

		List<List<Integer>> result = threeSum(arr);

		for (List<Integer> triplet : result) {
			System.out.println(triplet);
		}
	}

	private static List<List<Integer>> threeSum(int[] arr) {
		List<List<Integer>> result = new ArrayList<>();

		Arrays.sort(arr);

		for (int i = 0; i < arr.length - 2; i++) {
			int left = i + 1;
			int right = arr.length - 1;

			if (i > 0 && arr[i] == arr[i - 1])
				continue;

			while (left < right) {
				int sum = arr[i] + arr[left] + arr[right];

				if (sum == 0) {
					result.add(Arrays.asList(arr[i], arr[left], arr[right]));

					while (left < right && arr[left] == arr[left + 1])
						left++;
					while (left < right && arr[left] == arr[right - 1])
						right--;

					left++;
					right--;
				} else if (sum < 0) {
					left++;
				} else {
					right--;
				}
			}
		}

		return result;
	}

}
