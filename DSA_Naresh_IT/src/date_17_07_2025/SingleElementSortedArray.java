package date_17_07_2025;

public class SingleElementSortedArray {
	public static void main(String[] args) {
		int[] arr = { 1, 1, 2, 2, 3, 3, 4, 5, 5, 6, 6 };

		System.out.println("Single element is: " + singleElement(arr));
	}

	// Brute force TC: O(N) SC: O(1)
	static int singleElement(int[] arr) {
		int n = arr.length;

		if (n == 1) {
			return arr[0];
		}

		for (int i = 0; i < n; i++) {
			// check for first index
			if (i == 0) {
				if (arr[i] != arr[i + 1]) {
					return arr[i];
				}
			}
			// check for last index
			else if (i == n - 1) {
				if (arr[i] != arr[i - 1]) {
					return arr[i];
				}
			} else {
				if (arr[i] != arr[i - 1] && arr[i] != arr[i + 1]) {
					return arr[i];
				}
			}
		}
		return -1;
	}

}
