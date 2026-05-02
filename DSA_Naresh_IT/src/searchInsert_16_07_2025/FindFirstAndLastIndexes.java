package searchInsert_16_07_2025;

import java.util.Arrays;

public class FindFirstAndLastIndexes {
	public static void main(String[] args) {
		int[] arr = { 5, 7, 7, 8, 8, 10 };
		int target = 8;

		int[] searchRange = searchRange(arr, target);

		System.out.println(Arrays.toString(searchRange));

		int[] searchRangeWithBinary = searchRangeWithBinary(arr, target);

		System.out.println(Arrays.toString(searchRangeWithBinary));
	}

	// Brute force
	private static int[] searchRange(int[] arr, int target) {
		int start = -1;
		int end = -1;

		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == target) {
				if (start == -1) {
					start = i;
				}
				end = i;
			}
		}
		return new int[] { start, end };
	}

	// Binary search TC: O(logN) SC : O(1)
	private static int[] searchRangeWithBinary(int[] arr, int target) {
		int firstPosition = findBound(arr, target, true);
		int lastPosition = findBound(arr, target, false);

		return new int[] { firstPosition, lastPosition };
	}

	private static int findBound(int[] arr, int target, boolean isFirst) {
		int start = 0;
		int end = arr.length - 1;
		int bound = -1;

		while (start <= end) {
			int mid = start + (end - start) / 2;

			if (arr[mid] == target) {
				bound = mid;
				if (isFirst) {
					end = mid - 1;
				} else {
					start = mid + 1;
				}
			} else if (target > arr[mid]) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}

		return bound;
	}

}
