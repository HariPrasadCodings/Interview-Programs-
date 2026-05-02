package deloitte_april_2026.arrays;

public class BestTimeToBuyAndSellStock {
	public static void main(String[] args) {
		int[] nums = { 7, 1, 5, 3, 6, 4 };

		System.out.println(maxProfit(nums));
	}

	static int maxProfit(int[] nums) {
		int minPrice = Integer.MAX_VALUE;
		int maxProfit = 0;

		for (int num : nums) {
			if (num < minPrice) {
				minPrice = num;
			} else {
				int profit = num - minPrice;
				maxProfit = Math.max(profit, maxProfit);

			}
		}

		return maxProfit;
	}

}
