package date_09_07_2025;

public class FindFirstMimumValueInArray {
	public static void main(String[] args) {
		int[] arr = { 4, 5, 6, 1, 2, 7, 8, 9 };

		System.out.println("Minimum Element in Array is: " + findMinimum(arr));
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
