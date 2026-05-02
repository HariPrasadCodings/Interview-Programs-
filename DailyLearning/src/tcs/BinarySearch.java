package tcs;

import java.util.Arrays;

public class BinarySearch {

	public static int binarySearch(int[] arr, int target) {

		int left = 0;
		int right = arr.length - 1;
		while (left <= right) {
			int mid = left + (right - left) / 2;
			if (arr[mid] == target) {
				return mid;
			} else if (arr[mid] < target) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}

		return -1;
	}

	public static void main(String[] args) {
		int[] arr = { 5, 4, 2, 7, 8, 9, 12, 10 };
		Arrays.sort(arr); // 2,4,5,7,8,9,10,12
		System.out.println("Index of 8: " + binarySearch(arr, 8));
	}

}
