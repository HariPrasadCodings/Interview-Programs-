package arrays;

import java.util.Arrays;

public class CheckTwoArraysSameOrNot {
	public static void main(String[] args) {
		int[] arr1 = { 1, 2, 3, 4, 5 };
		int[] arr2 = { 1, 2, 3, 3, 5 };

		System.out.println(Arrays.equals(arr1, arr2));
	}

}
