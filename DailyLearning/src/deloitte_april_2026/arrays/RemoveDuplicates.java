package deloitte_april_2026.arrays;

/**
 * "I use a two-pointer approach to overwrite duplicates in-place, achieving
 * O(n) time and O(1) space."
 */
public class RemoveDuplicates {
	public static void main(String[] args) {
		int[] arr = { 0, 0, 1, 1, 1, 2, 2, 3, 3, 4 };

		System.out.println(removeDuplicates(arr));
	}

	static int removeDuplicates(int[] nums) {
		int i = 0;

		for (int j = 1; j < nums.length; j++) {
			if (nums[i] != nums[j]) {
				i++;
				nums[i] = nums[j];
			}
		}

		return i + 1;
	}

}
