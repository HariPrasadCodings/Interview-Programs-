package practise;

public class BestTimeToBuyAndSellStock {
	public static void main(String[] args) {
		int[] prices = { 7, 1, 5, 3, 6, 4 };

		System.out.println("Max profit is: " + findMaxProfit(prices));

	}

	private static int findMaxProfit(int[] prices) {
		int minPrice = Integer.MAX_VALUE;
		int maxProfit = 0;

		for (int price : prices) {
			if (price < minPrice) {
				minPrice = price;
			}
			int profit = price - minPrice;
			maxProfit = Math.max(profit, maxProfit);
		}

		return maxProfit;
	}

}
