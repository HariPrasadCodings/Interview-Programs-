package deloitte_april_2026.arrays.practise;

public class BestTimeToBuyAndSellStock1 {
	public static void main(String[] args) {
		int[] arr = {7,1,5,2,6,4};
		System.out.println("Max profit is: " + find(arr));
	}
	
	static int find(int[] prices) {
		int minprice = Integer.MAX_VALUE;
		int maxprofit = 0;
		
		for(int price : prices) {
			if(price < minprice) {
				minprice = price;
			} else {
				int profit = price - minprice;
				maxprofit = Math.max(maxprofit, profit);
			}
		}
		return maxprofit;
	}

}
