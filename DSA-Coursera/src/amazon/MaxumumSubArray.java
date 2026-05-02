package amazon;

public class MaxumumSubArray {
	public static void main(String[] args) {
		int[] nums1 = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
		int[] nums2 = { 1 };
		int[] nums3 = { 5, 4, -1, 7, 8 };

		System.out.println(maxSubArray(nums1));
		System.out.println(maxSubArray(nums2));
		System.out.println(maxSubArray(nums3));
	}

	private static int maxSubArray(int[] nums) {
		int maxCurrent = nums[0];
		int maxGlobal = nums[0];

		for (int i = 1; i < nums.length; i++) {
			maxCurrent = Math.max(nums[i], maxCurrent + nums[i]);
			if (maxCurrent > maxGlobal) {
				maxGlobal = maxCurrent;
			}
		}
		return maxGlobal;
	}

}
