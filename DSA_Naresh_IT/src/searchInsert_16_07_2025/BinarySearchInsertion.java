package searchInsert_16_07_2025;

public class BinarySearchInsertion {
	public static void main(String[] args) {
		int[] arr = { 4, 7, 9, 12, 15 };
		int target = -1;

		System.out.println(binarySearchInsert(arr, target));
	}

	// TC: O(logN) SC : O(1)
	static int binarySearchInsert(int[] arr, int target) {
		int start = 0;
		int end = arr.length - 1;

		while (start <= end) {
			int mid = start + (end - start) / 2;

			if (arr[mid] == target) {
				return mid;
			} else if (target > arr[mid]) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}

		}
		// At this point, left is the insertion index
		return start;
	}

}
