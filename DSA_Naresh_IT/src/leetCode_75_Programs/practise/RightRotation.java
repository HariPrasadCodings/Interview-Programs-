package leetCode_75_Programs.practise;

import java.util.Arrays;

public class RightRotation {
	public static void main(String[] args) {
		int[] arr = { 1, 2, 3, 4, 5, 6, 7 };
		int d = 3;

		rotateRight(arr, d);
		System.out.println(Arrays.toString(arr)); // 5,6,7,1,2,3,4

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

	static void rotateRight(int[] arr, int d) {
		int n = arr.length;
		d = d % n;

		rotateSubArray(arr, 0, n - 1);
		rotateSubArray(arr, d, n - 1);
		rotateSubArray(arr, 0, d - 1);
	}

}
