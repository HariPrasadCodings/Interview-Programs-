package deloitte_april_2026.arrays;

/**
 * I use a two-pointer approach where I overwrite unwanted values in-place and
 * return the new length.
 * 
 * ⏱️ Complexity Time: O(n) Space: O(1) (in-place)
 */
public class RemoveElement {
	public static void main(String[] args) {
		int[] arr = { 3, 2, 2, 3 };
		int val = 3;

		System.out.println(removeElement(arr, val));
	}

	private static int removeElement(int[] arr, int val) {

		int k = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != val) {
				arr[k++] = arr[i];
			}
		}

		return k;
	}

}
