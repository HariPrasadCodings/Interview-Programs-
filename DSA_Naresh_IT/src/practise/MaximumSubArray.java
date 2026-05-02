package practise;

public class MaximumSubArray {
	public static void main(String[] args) {
		int[] arr = { 5, 4, -1, 7, 8 };
		System.out.println(maxSum(arr));
	}

	static int maxSum(int[] arr) {
		int maxSum = arr[0];
		int currentsum = arr[0];

		for (int i = 1; i < arr.length; i++) {
			currentsum = Math.max(arr[i], currentsum + arr[i]);
			maxSum = Math.max(maxSum, currentsum);
		}
		return maxSum;
	}
}
