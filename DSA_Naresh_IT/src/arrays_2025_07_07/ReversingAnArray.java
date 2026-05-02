package arrays_2025_07_07;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ReversingAnArray {
	public static void main(String[] args) {
		int[] arr = { 4, 6, 2, 1, 7, 8 };

		int n = arr.length;
		int[] temp = new int[n];

//		int[] newArray = Arrays.copyOf(arr, arr.length);
//
//		// Approach: 1
//		// 1. Create an temporary array
//		// 2. Copy all the elements from original to temporary array in reverse order.
//		System.out.println(Arrays.toString(newArray));
//
//		for (int i = 0; i < arr.length; i++) {
//			newArray[i] = arr[arr.length - i - 1];
//		}
//
//		arr = newArray;
//
//		System.out.println(Arrays.toString(arr));

//		for (int i = 0; i < n; i++) {
//			temp[i] = arr[n - i - 1];
//		}
//
//		for (int i = 0; i < n; i++) {
//			arr[i] = temp[i];
//		}
//
//		System.out.println(Arrays.toString(arr));

		// Approach: 2: using two pointers
		int left = 0;
		int right = n - 1; // 5
		while (left < right) {
			int tem = arr[left]; // 4,
			arr[left] = arr[right]; // 8
			arr[right] = tem; // 4
			left++;
			right--;
		}

		// TC: o(n) SC: o(1)

		System.out.println(Arrays.toString(arr));

		// Approach: 3 Swapping elements

		int[] a = { 4, 8, 2, 7, 6, 9 };
		int n1 = a.length;

		for (int i = 0; i < n1 / 2; i++) {
			int temp1 = a[i];
			a[i] = a[n - i - 1];
			a[n - i - 1] = temp1;
		}
		System.out.println(Arrays.toString(a));

		// TC: o(n) SC: o(1)

		// Approach: 4 Using in-built methods
		int[] ar = { 9, 8, 7, 6, 5, 4 };

		List<Integer> list = Arrays.stream(ar).boxed().collect(Collectors.toList());

		Collections.reverse(list);

		System.out.println(list);

		// TC: o(n) SC: o(1)

	}

}
