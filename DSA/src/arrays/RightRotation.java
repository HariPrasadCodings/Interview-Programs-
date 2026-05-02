package arrays;

import java.util.Arrays;

public class RightRotation {
	public static void main(String[] args) {
		int[] arr = { 1, 2, 3, 4, 5, 6, 7 };

		rightRotate(arr, 3); // 5,6,7,1,2,3,4
	}

	static void rightRotate(int[] arr, int d) {
		int n = arr.length;

		d = d % n;

		rotate(arr, 0, n - 1); // 7,6,5,4,3,2,1

		rotate(arr, d, n - 1); // 7,6,5,1,2,3,4

		rotate(arr, 0, d - 1); // 5,6,7,1,2,3,4

		System.out.println(Arrays.toString(arr));
	}

	static void rotate(int[] arr, int left, int right) {
		while (left <= right) {
			int temp = arr[left];
			arr[left] = arr[right];
			arr[right] = temp;
			left++;
			right--;
		}
	}

}
