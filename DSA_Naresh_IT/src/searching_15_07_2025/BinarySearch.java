package searching_15_07_2025;

public class BinarySearch {
	public static void main(String[] args) {
		int[] arr = { -1, 0, 3, 5, 9, 12 };
		int target = 9; // at index 4
		System.out.println(binarySearchUsingIterativeApproach(arr, target));

		System.out.println(binarySearchUsingRecursive(arr, target, 0, arr.length - 1));
	}

	// Approach 1: Using Iterative TC: logn
	static int binarySearchUsingIterativeApproach(int[] arr, int target) {
		int left = 0;
		int right = arr.length - 1;

		while (left <= right) {
			int mid = left + (right - left) / 2;

			if (arr[mid] == target) {
				return mid;
			}
			if (target > arr[mid]) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}

		return -1;

	}

	static int binarySearchUsingRecursive(int[] arr, int target, int left, int right) {
		if (left > right) {
			return -1;
		}
		int mid = left + (right - left) / 2;

		if (arr[mid] == target) {
			return mid;
		}
		if (target > arr[mid]) {
			return binarySearchUsingRecursive(arr, target, mid + 1, right);
		} else {
			return binarySearchUsingRecursive(arr, target, left, mid - 1);
		}
	}

}
