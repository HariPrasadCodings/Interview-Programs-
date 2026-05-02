package deloitte_april_2026.arrays;

/**
 * "I greedily capture every upward price movement to maximize profit with
 * multiple transactions."
 */
public class BestTimeToBuyAndSellStock2 {
	public static void main(String[] args) {
		int[] nums = { 7, 1, 5, 3, 6, 4 };

		System.out.println(bestTimeToBuyAndSellStock(nums));
	}

	static int bestTimeToBuyAndSellStock(int[] nums) {
		int profit = 0;

		for (int i = 1; i < nums.length; i++) {
			if (nums[i] > nums[i - 1]) {
				profit = profit + nums[i] - nums[i - 1];
			}
		}

		return profit;
	}

}
