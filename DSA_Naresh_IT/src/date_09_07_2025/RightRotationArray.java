package date_09_07_2025;

import java.util.Arrays;

public class RightRotationArray {
	public static void main(String[] args) {
		int[] arr = { 1, 2, 3, 4, 5, 6, 7 }; // op: 5,6,7,1,2,3,4

		int d = 3;

		rightRotate(arr, d);

		System.out.println("After right rotating an " + d + " positions: " + Arrays.toString(arr));

	}

	static void rightRotate(int[] arr, int d) {
		int n = arr.length;

		d = d % n;

		// 1. Reverse entire array
		reverseSubArray(arr, 0, n - 1);

		// 2. Reverse d elements
		reverseSubArray(arr, 0, d - 1);

		// 3. Reverse n-d elements
		reverseSubArray(arr, d, n - 1);
	}

	static void reverseSubArray(int[] arr, int start, int end) {
		while (start < end) {
			int temp = arr[start];
			arr[start] = arr[end];
			arr[end] = temp;
			start++;
			end--;
		}

	}

}
