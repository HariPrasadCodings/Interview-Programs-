package practise.array;

import java.util.Arrays;

public class ProductOfArrayExceptItself {
	public static void main(String[] args) {
		int[] arr = { 1, 2, 3, 4, 5, 6 };
		int[] product = product(arr);
		System.out.println(Arrays.toString(product)); // 720

	}

	static int[] product(int[] arr) {
		int[] result = new int[arr.length];
		result[0] = 1;
		for (int i = 1; i < arr.length; i++) {
			result[i] = result[i - 1] * arr[i - 1];
		}
		int rightProduct = 1;
		for (int i = arr.length - 1; i >= 0; i--) {
			result[i] = rightProduct * result[i];
			rightProduct = rightProduct * arr[i];
		}
		return result;
	}

}
