package deloitte_april_2026.arrays;

import java.util.Arrays;

/**
 * First I store prefix products in result array,then I traverse from right
 * while maintaining suffix product and multiply it with prefix to get final
 * answer.
 */
public class ProductOfArray {
	public static void main(String[] args) {
		int[] arr = { 1, 2, 3, 4 };

		int[] result = productOfArray(arr);

		System.out.println(Arrays.toString(result));
	}

	static int[] productOfArray(int[] arr) {
		int n = arr.length;
		int[] result = new int[n];

		// step:1 prefix product
		result[0] = 1;
		for (int i = 1; i < n; i++) {
			result[i] = result[i - 1] * arr[i - 1];
		}

		// step:2 suffix product
		int suffix = 1;
		for (int i = n - 1; i >= 0; i--) {
			result[i] = result[i] * suffix;
			suffix = suffix * arr[i];

		}

		return result;
	}
}
