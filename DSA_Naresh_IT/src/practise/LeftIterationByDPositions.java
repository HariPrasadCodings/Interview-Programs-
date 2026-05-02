package practise;

import java.util.Arrays;

public class LeftIterationByDPositions {
	public static void main(String[] args) {
		int[] arr = { 1, 2, 3, 4, 5, 6 }; // 4,5,6,1,2,3
		int d = 3;

		System.out.println("Original Array: " + Arrays.toString(arr));

		leftRotate(arr, d);

		System.out.println("After left rotation by " + d + " positions: " + Arrays.toString(arr));
	}

	static void leftRotate(int[] arr, int d) {

		int n = arr.length;

		d = d % n;

		// Reverse the first k elements
		rotateSubArray(arr, 0, d - 1);

		// Reverse the n-d elements
		rotateSubArray(arr, d, n - 1);

		// Reverse the total array
		rotateSubArray(arr, 0, n - 1);
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
