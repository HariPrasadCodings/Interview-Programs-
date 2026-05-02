package practise;

import java.util.Arrays;

public class RightIterationByDPositions {
	public static void main(String[] args) {
		int[] arr = { 1, 2, 3, 4, 5, 6, 7 }; // 5,6,7,1,2,3,4
		int d = 3;

		System.out.println("Original Array: " + Arrays.toString(arr));

		rightRotate(arr, d);

		System.out.println("After Right rotation by " + d + " postions: " + Arrays.toString(arr));
	}

	static void rightRotate(int[] arr, int d) {
		int n = arr.length;

		d = d % n;

		// 1. Reverse the total elements
		rotateSubArray(arr, 0, n - 1);

		// 2. Reverse the d elements
		rotateSubArray(arr, 0, d - 1);

		// 3. Reverse the n-d elements
		rotateSubArray(arr, d, n - 1);

	}

	static void rotateSubArray(int[] arr, int left, int right) {
		while (left < right) {
			int temp = arr[left];
			arr[left] = arr[right];
			arr[right] = temp;

			left++;
			right--;
		}
	}

}
