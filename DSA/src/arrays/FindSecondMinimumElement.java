package arrays;

public class FindSecondMinimumElement {
	public static void main(String[] args) {
		int[] arr = { 4, 6, 7, 8, 1, 2, 3, 9 };
		System.out.println("Second Minimum element is: " + findSecondMinimum(arr));
	}

	static int findSecondMinimum(int[] arr) {
		int min = arr[0];
		int secondMin = -1;

		for (int num : arr) {
			if (num < min) {
				secondMin = min;
				min = num;
			} else if (num < secondMin && num != min) {
				secondMin = num;
			}
		}
		return secondMin;
	}

}
