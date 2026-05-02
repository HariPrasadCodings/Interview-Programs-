package arrays_2025_07_07;

import java.util.Arrays;

/**
 * Write a program to left rotate an array by d positions.
 */
public class RotateAnArrayByDPostions {
	public static void main(String[] args) {

		int[] arr = { 10, 20, 30, 40, 50, 60, 70, 80, 90, 100 };
		int d = 3;

		rotateArray(arr, d);

		// Reversed array: {40,50,60,70,80,90,100,10,20,30}

		// Approach : 1 Brute force approach

	}

//	private static void bruteForce(int[] arr, int d) {
//		int temp = arr[0];
//		for (int i = 0; i < arr.length - 1; i++) {
//			arr[i] = arr[i + 1];
//
//			temp = arr[arr.length - 1];
//		}
//		arr[arr.length - 1] = temp;
//		System.out.println(Arrays.toString(arr));
//	}

	// TC: O(n * d) : the outer loop runs d times, and within each iteration, the
	// inner loop shifts all n elements of the array by one position
	// resulting in a total n * d operations
	// SC: O(1)
	static void rotateArray(int[] arr, int d) {
		int n = arr.length;
		for (int i = 0; i < d; i++) {
			int first = arr[0];
			for (int j = 0; j < n; j++) {
				arr[j] = arr[j + 1];
			}
			arr[n - 1] = first;
		}
	}

	// Approach: 2 Using Temporary array
	static void usingTemporaryArray(int[] arr, int d) {
		int[] temp = new int[d];
		int n = arr.length;
		for (int i = 0; i < d; i++) {
			temp[i] = arr[i];
		}

		// step:2 shift all these elements n-d

		for (int i = d; i < n; i++) {
			arr[i - d] = arr[i];
		}

		// step: 3 copy all temp elements into

		for (int i = 0; i < d; i++) {
			arr[n - d + i] = temp[i];
		}
		
		System.out.println(Arrays.toString(arr));
		
		// TC: O(d) + O(n-d) + O(d) = O(n+d)
		// SC: O(d)
	}

}
