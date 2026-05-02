package arrays;

public class FindMaximumElement {
	public static void main(String[] args) {
		int[] arr = {9, 8, 3, 4, 5, 6, 10};
		System.out.println("Maximum Element : " + findMaximumElement(arr));

	}

	private static int findMaximumElement(int[] arr) {
		if (arr.length == 0)
			return 0;

		int max = arr[0];
		for (int num : arr) {
			if (num > max) {
				max = num;
			}
		}
		return max;
	}

}
