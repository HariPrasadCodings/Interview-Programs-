package arrays_14_07_2025;

/**
 * https://leetcode.com/problems/sort-colors/submissions/1697638727/
*/
import java.util.Arrays;

public class SortColors {
	public static void main(String[] args) {
		int[] arr = { 2, 0, 2, 1, 1, 0 };

		countSort(arr);

		System.out.println("Sorting using count sort: " + Arrays.toString(arr));

		int[] arr1 = { 2, 0, 1 };

		dutchSorting(arr1);

		System.out.println("Sorting using dutch sort: " + Arrays.toString(arr1));
	}

	// Approach : 1 Counting Sort TC: O(N) + O(N) SC: O(1)
	static void countSort(int[] arr) {
		int count0 = 0;
		int count1 = 0;
		int count2 = 0;

		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == 0) {
				count0++;
			} else if (arr[i] == 1) {
				count1++;
			} else {
				count2++;
			}
		}

		// Overwrite
		int index = 0;
		for (int i = 0; i < count0; i++) {
			arr[index++] = 0;
		}
		for (int i = 0; i < count1; i++) {
			arr[index++] = 1;
		}
		for (int i = 0; i < count2; i++) {
			arr[index++] = 2;
		}
	}

	// Approach 2: Dutch National flag algorithm TC: O(N) SC: O(1)
	static void dutchSorting(int[] arr) {
		int start = 0;
		int mid = 0;
		int end = arr.length - 1;

		while (start <= end) {
			switch (arr[mid]) {
			case 0:
				swap(arr, start, mid);
				mid++;
				start++;
				break;

			case 1:
				mid++;
				break;

			case 2:
				swap(arr, mid, end);
				end--;
				break;

			default:
				System.out.println("No values to swap");
			}
		}

	}

	static void swap(int[] arr, int pos1, int pos2) {
		while (pos1 < pos2) {
			int temp = arr[pos1];
			arr[pos1] = arr[pos2];
			arr[pos2] = temp;
			pos1++;
			pos2--;
		}
	}

}
