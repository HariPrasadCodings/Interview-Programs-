package practise;

public class BestTimeToBuyAndSellStock {
	public static void main(String[] args) {
		int[] arr = { 7, 1, 5, 3, 6, 4 };

		System.out.println(maxProfilt(arr));

	}

//TC: O(n) - Single pass through the array.
//	SC: O(1) - Only two variables used.
	static int maxProfilt(int[] arr) {
		int minPrice = Integer.MAX_VALUE;
		int maxPrice = 0;

		for (int price : arr) {
			minPrice = Math.min(minPrice, price);
			maxPrice = Math.max(maxPrice, price - minPrice);
		}
		return maxPrice;
	}

}
