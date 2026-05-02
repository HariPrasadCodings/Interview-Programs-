package arrays;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Problem: Rotate an array to the right by k steps.
 */
/**
 * Problem: Rotate an array to the right by k steps.
 */
public class RotateArray {
	public static void main(String[] args) {
		int[] arr = {1, 2, 3, 4, 5, 6, 7}; // Initial array
		rotate(arr, 4); // Rotate the array to the right by 4 steps
		System.out.println(Arrays.toString(arr)); // Print the rotated array

		int[] arr1 = {1, 2, 3, 4, 5, 6, 7};
		rotateElements(arr1);
	}

	private static void reverse(int[] arr, int start, int end) {
		// Swap elements from start to end moving toward the center
		while (start < end) {
			int temp = arr[start]; // Store the start element
			arr[start] = arr[end]; // Replace start with end
			arr[end] = temp; // Put the start element at the end
			start++; // Move start forward
			end--; // Move end backward
		}
	}

	private static void rotate(int[] arr, int k) {
		k = k % arr.length; // Handle cases where k > array length

		// Step 1: Reverse the entire array
		reverse(arr, 0, arr.length - 1);

		// Step 2: Reverse the first k elements
		reverse(arr, 0, k - 1);

		// Step 3: Reverse the remaining n-k elements
		reverse(arr, k, arr.length - 1);
	}

	// using Collections.rotate method
	private static void rotateElements(int[] arr) {
		List<Integer> list = Arrays.stream(arr).boxed()
				.collect(Collectors.toList());
		Collections.rotate(list, 4);
		System.out.println(list);
	}
}
