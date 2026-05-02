package deloitte_april_2026.arrays.practise;

import java.util.Arrays;

public class ProductOfArrayExceptItself {
	public static void main(String[] args) {
		int[] arr = { 1, 2, 3, 4 };
		int[] product = product(arr);

		System.out.println(Arrays.toString(product));
	}

	static int[] product(int[] arr) {
		int n = arr.length;
		int[] result = new int[n];

		result[0] = 1;
		// prfix product
		for (int i = 1; i < n; i++) {
			result[i] = result[i - 1] * arr[i - 1];
		}

		// suffix product
		int suffix = 1;
		for (int i = n - 1; i >= 0; i--) {
			result[i] = result[i] * suffix;
			suffix = suffix * arr[i];
		}
		return result;
	}

}
