package practise;

public class FindMaxElementInArray {
	public static void main(String[] args) {
		int[] arr = { 4, 5, 6, 1, 2, 3, 8, 3 };
		System.out.println("Maximum element is: " + findMax(arr));
	}

	static int findMax(int[] arr) {
		int max = arr[0];

		for (int num : arr) {
			if (num > max) {
				max = num;
			}
		}
		return max;
	}

}
