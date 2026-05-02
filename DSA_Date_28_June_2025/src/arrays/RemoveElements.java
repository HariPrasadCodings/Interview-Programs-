package arrays;

public class RemoveElements {
	public static void main(String[] args) {
		int[] nums = {3, 2, 2, 3};
		int value = 3; // need to remove
		int k = removeElement(nums, value);
		System.out.println("k = " + k);
		System.out.print("Updated array: [");
		for (int i = 0; i < k; i++) {
			System.out.print(nums[i] + (i == k - 1 ? "" : ", "));
		}
		System.out.println("]");
		int[] arr = {0, 1, 2, 2, 3, 0, 4, 2};
		int value1 = 2;
		int k1 = removeElement(arr, value1);
		System.out.println("k1 = " + k1);
		System.out.print("Updated array: [");
		for (int i = 0; i < k1; i++) {
			System.out.print(arr[i] + (i == k1 - 1 ? "" : ", "));
		}
		System.out.println("]");
	}

	private static int removeElement(int[] arr, int val) {
		int k = 0;

		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != val) {
				arr[k] = arr[i];
				k++;
			}
		}
		return k;
	}

}
