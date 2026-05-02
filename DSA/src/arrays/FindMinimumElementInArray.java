package arrays;

public class FindMinimumElementInArray {
	public static void main(String[] args) {
		int[] arr = { 5, 7, 8, 1, 2, 9, 10, 4 };

		System.out.println("Minimum element is: " + findMinimum(arr));
	}

	static int findMinimum(int[] arr) {
		int min = arr[0];

		for (int num : arr) {
			if (num < min) {
				min = num;
			}
		}
		return min;
	}
}
