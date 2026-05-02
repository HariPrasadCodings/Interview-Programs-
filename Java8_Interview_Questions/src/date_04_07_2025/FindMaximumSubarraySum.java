package date_04_07_2025;

public class FindMaximumSubarraySum {
	public static void main(String[] args) {
		int[] arr = {34, -50, 42, 14, -5, 86};

		System.out.println("Max subarray is: " + findMaxSubArray(arr));
	}

	private static int findMaxSubArray(int[] arr) {

		int maxSoFar = arr[0];
		int maxEndingHere = arr[0];

		for (int i = 1; i < arr.length; i++) {
			maxEndingHere = Math.max(arr[i], maxEndingHere + arr[i]);
			maxSoFar = Math.max(maxSoFar, maxEndingHere);
		}

		return maxSoFar;
	}

}
