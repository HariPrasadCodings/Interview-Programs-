package leetCode_75_Programs.arrays;

public class BestTimeToBuyAndSellStock {
	public static void main(String[] args) {
		int[] arr = { 7, 1, 5, 3, 6, 4 };

		System.out.println("Maximum profit is: " + findMaxProfit(arr));
	}

	// TC: O(n) - Single pass through the array.
	// SC: O(1) - Only two variables used.
	static int findMaxProfit(int[] arr) {
		int minPrice = Integer.MAX_VALUE;

		int maxProfit = 0;

		for (int price : arr) {
			minPrice = Math.min(minPrice, price);

			maxProfit = Math.max(maxProfit, price - minPrice);
		}
		return maxProfit;
	}

}
