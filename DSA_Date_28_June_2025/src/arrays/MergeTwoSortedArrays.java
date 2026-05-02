package arrays;

import java.util.Arrays;
import java.util.stream.Stream;

public class MergeTwoSortedArrays {
	public static void main(String[] args) {
		int[] nums1 = {1, 2, 3, 0, 0, 0};
		int m = 3;
		int[] nums2 = {2, 5, 6};
		int n = 3;
		System.out.println(
				Arrays.toString(mergeSortedArrays(nums1, m, nums2, n)));

		merge(nums1, m, nums2, n);
		System.out.println(Arrays.toString(nums1));

		int[] arr1 = {1};
		int m1 = 1;
		int[] arr2 = {0};
		int n1 = 0;
		merge(arr1, m1, arr2, n1);
		System.out.println(Arrays.toString(arr1));
		System.out.println(
				Arrays.toString(mergeSortedArrays(arr1, m1, arr2, n1)));

		int[] arr3 = {0};
		int m2 = 0;
		int[] arr4 = {1};
		int n2 = 1;

		merge(arr3, m2, arr4, n2);
		System.out.println(Arrays.toString(arr3));
		System.out.println(
				Arrays.toString(mergeSortedArrays(arr3, m2, arr4, n2)));
	}

	// using java 8 streams
	private static Integer[] mergeSortedArrays(int[] arr1, int m, int[] arr2,
			int n) {
		return Stream
				.concat(Arrays.stream(arr1, 0, m).boxed(),
						Arrays.stream(arr2, 0, n).boxed())
				.sorted().toArray(Integer[]::new);
	}

	// using Traditional way
	private static void merge(int[] arr1, int m, int[] arr2, int n) {
		int i = m - 1;
		int j = n - 1;
		int k = m + n - 1;

		while (i >= 0 && j >= 0) {
			if (arr1[i] > arr2[j]) {
				arr1[k--] = arr1[i--];
			} else {
				arr1[k--] = arr2[j--];
			}
		}

		while (j >= 0) {
			arr1[k--] = arr2[j--];
		}

	}
}
