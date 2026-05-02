package arrays;

public class RemoveDuplicatesFromSortedArray {
	public static void main(String[] args) {
		int[] arr1 = { 1, 1, 2 };
		int[] arr2 = { 0, 0, 1, 1, 1, 2, 2, 3, 3, 4 };
		int result1 = removeDuplicates(arr1);
		System.out.println(result1);
	}

	private static int removeDuplicates(int[] arr) {
		if (arr.length == 0) {
			return 0;
		}
		int i = 0;
		for (int j = 0; j < arr.length; j++) {
			if (arr[j] != arr[i]) {
				i++;
				arr[i] = arr[j];
			}
		}
		return i + 1;
	}

}
