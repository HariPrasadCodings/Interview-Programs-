package practise;

/**
 * 26. Remove Duplicates from Sorted Array
 * 
 * @author hkathi
 * 
 * @date : FEB - 24 - 2025
 */

public class RemoveDuplicates {
	public static void main(String[] args) {
		int[] arr = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
		System.out.println(removeDuplicates(arr));
	}

	private static int removeDuplicates(int[] arr) {

		if (arr.length == 0)
			return 0;
		int i = 0;

		for (int j = 1; j < arr.length; j++) {
			if (arr[j] != arr[i]) {
				i++;
				arr[i] = arr[j];
			}
		}

		return i + 1;
	}

}
