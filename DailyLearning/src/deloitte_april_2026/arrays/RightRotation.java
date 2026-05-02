package deloitte_april_2026.arrays;

import java.util.Arrays;

/**
 * "I’m using the reversal algorithm, where I reverse the entire array and then
 * " + "reverse parts to achieve rotation in O(n) time and O(1) space."
 */

public class RightRotation {
	public static void main(String[] args) {
		int[] arr = { 1, 2, 3, 4, 5, 6, 7 };
		int k = 3;

		rightRotation(arr, k);

		System.out.println(Arrays.toString(arr));
	}

	private static void rightRotation(int[] arr, int k) {

		int n = arr.length;

		k = k % n;

		// Rotate entire array --> 7,6,5,4,3,2,1
		rotateSubArray(arr, 0, n - 1);

		// Rotate n-k elements --> 7,6,5,1,2,3,4
		rotateSubArray(arr, k, n - 1);

		// Rotate first k elements --> 5,6,7,1,2,3,4
		rotateSubArray(arr, 0, k - 1);

	}

	private static void rotateSubArray(int[] arr, int i, int j) {
		while (i < j) {
			int temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;

			i++;
			j--;
		}

	}

}
