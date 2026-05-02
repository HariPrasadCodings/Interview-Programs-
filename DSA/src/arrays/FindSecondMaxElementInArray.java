package arrays;

public class FindSecondMaxElementInArray {
	public static void main(String[] args) {
		int[] arr = { 5, 7, 8, 2, 3, 4, 9 };

		System.out.println("Second maximum element is: " + findSecondMax(arr));
	}

	static int findSecondMax(int[] arr) {
		int max = arr[0];
		int secondMax = -1;

		for (int num : arr) {
			if (num > max) {
				secondMax = max;
				max = num;
			} else if (num > secondMax && num < max) {
				secondMax = num;
			}
		}
		return secondMax;
	}
}
