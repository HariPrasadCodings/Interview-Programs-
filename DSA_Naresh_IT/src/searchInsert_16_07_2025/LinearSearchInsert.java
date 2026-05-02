package searchInsert_16_07_2025;

public class LinearSearchInsert {
	public static void main(String[] args) {
		int[] arr = { 4, 7, 9, 12, 15 };
		int target = 1;

		System.out.println(insertLinearSeach(arr, target));
	}

	// TC : O(N) SC : O(1)
	public static int insertLinearSeach(int[] arr, int target) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] >= target) {
				return i;
			}
		}
		return arr.length;
	}

}
