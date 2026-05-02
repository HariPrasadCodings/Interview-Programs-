package arrays;

import java.util.Arrays;

public class ReversingAnArray {
	public static void main(String[] args) {
		int[] arr = { 1, 4, 7, 2, 9, 12, 6 };

		reverse(arr);

		System.out.println("After reversing an array: " + Arrays.toString(arr));
	}

	static void reverse(int[] arr) {
		if (arr.length <= 1) {
			return;
		}

		int start = 0;
		int end = arr.length - 1;

		while (start <= end) {
			int temp = arr[start];
			arr[start] = arr[end];
			arr[end] = temp;

			start++;
			end--;
		}
	}

}
