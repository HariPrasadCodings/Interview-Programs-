package arrays;

import java.util.Arrays;

public class FindSmallestMissingNumber {
	public static void main(String[] args) {
		int[] arr = { 2, 4, 6, 1 };

		int n = arr.length;
		boolean[] present = new boolean[n + 1];

		for (int num : arr) {
			if (num > 0 && num <= n + 1) {
				present[num] = true;
			}

		}

		for (int i = 1; i <= n + 1; i++) {
			if (!present[i]) {
				System.out.println("Smallest missing number is: " + i);
				break;
			}
		}

		findMissingNumber(arr);
	}

	private static void findMissingNumber(int[] arr) {
		int res = 1;
		Arrays.sort(arr);

		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == res) {
				res++;
			} else {
				System.out.println(res);
				break;
			}
		}
	}
}
