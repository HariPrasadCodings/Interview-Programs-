package practise;

public class FindMinimumElement {
	public static void main(String[] args) {

		int[] arr = { 9, 7, 3, 4, 5, 1, 10, 12 };
		System.out.println("Minimum Element is: " + findMinElement(arr));

	}

	static int findMinElement(int[] arr) {
		int min = arr[0];

		for (int num : arr) {
			if (num < min) {
				min = num;
			}
		}
		return min;
	}
}
