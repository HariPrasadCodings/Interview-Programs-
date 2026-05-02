package arrays;

/**
 * Given an integer array nums sorted in non-decreasing order, remove the
 * duplicates in-place such that each unique element appears only once. The
 * relative order of the elements should be kept the same. Then return the
 * number of unique elements in nums.
 * 
 * Consider the number of unique elements of nums to be k, to get accepted, you
 * need to do the following things:
 * 
 * Change the array nums such that the first k elements of nums contain the
 * unique elements in the order they were present in nums initially. The
 * remaining elements of nums are not important as well as the size of nums.
 * Return k.
 */

public class RemoveDuplicatesFromSortedArray {
	public static void main(String[] args) {
		int[] arr1 = {1, 1, 2};
		int k = removeDuplicates(arr1);
		System.out.println(k);
		System.out.print("Updated array: [");
		for (int i = 0; i < k; i++) {
			System.out.print(arr1[i] + (i == k - 1 ? "" : ", "));
		}
		System.out.println("]");

		int[] arr2 = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
		int k1 = removeDuplicates(arr2);
		System.out.println(k1);
		System.out.print("Updated array: [");
		for (int i = 0; i < k1; i++) {
			System.out.print(arr2[i] + (i == k1 - 1 ? "" : ", "));
		}
		System.out.println("]");
	}

	private static int removeDuplicates(int[] arr) {
		if (arr.length == 0)
			return 0;
		int k = 0;
		for (int j = 0; j < arr.length; j++) {
			if (arr[k] != arr[j]) {
				k++;
				arr[k] = arr[j];

			}
		}
		return k + 1;
	}

}
