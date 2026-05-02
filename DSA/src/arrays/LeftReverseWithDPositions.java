package arrays;

import java.util.Arrays;

public class LeftReverseWithDPositions {
	public static void main(String[] args) {
		int[] arr = { 1, 2, 3, 4, 5, 6, 7 }; // 4,5,6,7,1,2,3
		int d = 3;
		
		System.out.println(Arrays.toString(arr));

		leftRotate(arr, d);

	}

	static void leftRotate(int[] arr, int d) {
		int n = arr.length;

		d = d % n;
		// Step 1: Reverse first d elements
		rotate(arr, 0, d - 1);

		System.out.println(Arrays.toString(arr)); // 3,2,1,4,5,6,7

		// Step 2: Reverse the rest(n-d) elements
		rotate(arr, d, n - 1);
		System.out.println(Arrays.toString(arr)); // 3,2,1,7,6,5,4

		// Step 3: Reverse the whole array
		rotate(arr, 0, n - 1);

		System.out.println(Arrays.toString(arr)); // 4,5,6,7,1,2,3
	}

	static void rotate(int[] arr, int left, int end) {
		while (left <= end) {
			int temp = arr[left];
			arr[left] = arr[end];
			arr[end] = temp;
			left++;
			end--;
		}
	}

}
