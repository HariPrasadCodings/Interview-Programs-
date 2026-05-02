package leetCode_75_Programs.practise;

import java.util.Arrays;

public class LeftRotation {
	public static void main(String[] args) {
		int[] arr = { 1, 2, 3, 4, 5, 6, 7 }; // 4,5,6,7,1,2,3
		int d = 3;

		rotateLeft(arr, d);

		System.out.println(Arrays.toString(arr));
	}

	static void rotateLeft(int[] arr, int d) {
		int n = arr.length;
		d = d % n;

		rotateSubArray(arr, 0, d - 1);

		rotateSubArray(arr, d, n - 1);

		rotateSubArray(arr, 0, n - 1);
	}

	static void rotateSubArray(int[] arr, int start, int end) {
		while (start < end) {
			int temp = arr[start];
			arr[start] = arr[end];
			arr[end] = temp;

			start++;
			end--;
		}
	}

}
