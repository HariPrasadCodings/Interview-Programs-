package deloitte_april_2026.arrays;

import java.util.Arrays;

public class LeftRotation {
	public static void main(String[] args) {
		int[] arr = { 1, 2, 3, 4, 5, 6, 7 };
		int k = 3;

		leftRotate(arr, k);

		System.out.println(Arrays.toString(arr));
	}

	/*
	 * Reverse first k elements Reverse remaining n-k elements Reverse entire array
	 */
	static void leftRotate(int[] arr, int k) {

		int n = arr.length;
		k = k % n;

		// Rotate first k elements // 3,2,1,4,5,6,7
		rotatesubArray(arr, 0, k - 1);

		// Rotate n -k elements // 3,2,1,7,6,5,4
		rotatesubArray(arr, k, n - 1);

		// Rotate entire array // 4,5,6,7,1,2,3
		rotatesubArray(arr, 0, n - 1);
	}

	static void rotatesubArray(int[] arr, int i, int j) {
		while (i < j) {
			int temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;

			i++;
			j--;
		}
	}

}
