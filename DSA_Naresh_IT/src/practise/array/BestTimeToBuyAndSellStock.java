package practise.array;

/**
 * Problem: Given an array prices where prices[i] is the price on day i, find
 * the maximum profit by choosing one day to buy and another to sell.
 */

public class BestTimeToBuyAndSellStock {
	public static void main(String[] args) {

		int[] prices = { 1, 2, 3, 6, 8, 10 };

		int maxProfit = maxProfit(prices);
		System.out.println("Maximum profit is: " + maxProfit);

	}

	/**
	 * Explanation: Track the minimum price seen so far and compute the potential
	 * profit at each step. This avoids checking all pairs (O(n²)) and achieves
	 * O(n). Edge cases include arrays with one element (profit = 0).
	 */
	static int maxProfit(int[] prices) {
		int maxProfit = Integer.MIN_VALUE;
		int minPrice = Integer.MAX_VALUE;

		for (int price : prices) {
			minPrice = Math.min(minPrice, price);
			// 214XXXXXX, 1 = 1 in every iteration it wont change since the min value is 1
			// among all the values
			maxProfit = Math.max(maxProfit, price - minPrice);
			// 1st Iteration -214XXXXXX, 1-1 = 0
			// 2nd Iteration 0, 2-1 = 1
			// 3rd Iteration 1, 3-1 = 2
			// 4th Iteration 2, 6-1 = 5
			// 5th Iteration 5, 8-1 = 7
			// 6th Iteration 7, 10-1 = 9
		}
		return maxProfit;
	}

}
