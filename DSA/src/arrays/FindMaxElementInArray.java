package arrays;

public class FindMaxElementInArray {
	public static void main(String[] args) {
		int[] arr = { 2, 4, 6, 8, 9, 10, 1, 5 };

		System.out.println("Maximum Element is: " + findMax(arr));
	}

	static int findMax(int[] arr) {
		if (arr.length <= 0) {
			return arr[0];
		} else {
			int max = arr[0];

			for (int num : arr) {
				if (num > max) {
					max = num;
				}
			}

			return max;
		}
	}

}
