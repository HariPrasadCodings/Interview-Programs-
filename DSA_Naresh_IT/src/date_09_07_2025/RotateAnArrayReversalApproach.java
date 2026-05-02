package date_09_07_2025;

import java.util.Arrays;

/**
 * Array left rotations by d postions:
 */
public class RotateAnArrayReversalApproach {
	public static void main(String[] args) {
		int[] arr = { 1, 2, 3, 4, 5, 6, 7 }; // n = 7; 6 indexes; d : 3 positions i.e 1,2,3; n-d = 4,5,6,7
		int d = 3;

		System.out.println("Original Array: " + Arrays.toString(arr));

		leftRotate(arr, d);

		System.out.println("After rotat an " + d + " positions: " + Arrays.toString(arr));

	}

	static void leftRotate(int[] arr, int d) {
		int n = arr.length;
		// Ensure is d is within the bounds
		d = d % n;
		// Step 1: Rotate the first k elements
		reverseSubArray(arr, 0, d - 1);
		// Step 2: Rotate the n-d elements
		reverseSubArray(arr, d, n - 1);
		// Step 3: Reverse the entire array
		reverseSubArray(arr, 0, n - 1);
	}

	// Function to reverse elements between two indices
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
