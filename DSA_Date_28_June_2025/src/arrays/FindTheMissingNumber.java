package arrays;

public class FindTheMissingNumber {
	public static void main(String[] args) {
		int[] nums = {0, 1, 2, 4};

		int n = nums.length;

		int totalSum = n * (n + 1) / 2;
		int actualSum = 0;

		for (int i = 0; i < n; i++) {
			actualSum = actualSum + nums[i];
		}
		System.out.println("Missing Number: " + (totalSum - actualSum));

		System.out.println(findMissingNumber(nums));
	}

	// using for each loop approach
	private static int findMissingNumber(int[] arr) {
		int n = arr.length;
		int totalSum = n * (n + 1) / 2;
		int actualSum = 0;

		for (int result : arr) {
			actualSum = actualSum + result;
		}
		return totalSum - actualSum;
	}
}
