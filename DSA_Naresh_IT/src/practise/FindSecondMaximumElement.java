package practise;

public class FindSecondMaximumElement {
	public static void main(String[] args) {
		int[] arr = { 1, 6, 7, 2, 3, 4, 5, 9 };

		System.out.println("Second Maximum element is: " + findSecondMax(arr));
	}

	static int findSecondMax(int[] arr) {
		int max = arr[0];
		int secondMax = -1;

		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > max) {
				secondMax = max;
				max = arr[i];
			} else if (arr[i] > secondMax && arr[i] < max) {
				secondMax = arr[i];
			}
		}
		return secondMax;
	}

}
