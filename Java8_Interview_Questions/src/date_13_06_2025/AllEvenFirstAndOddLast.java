package date_13_06_2025;

public class AllEvenFirstAndOddLast {
	public static void main(String[] args) {
		int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
		isEvenFirstAndOddLast(arr);
	}

	private static void isEvenFirstAndOddLast(int[] arr) {
		int[] result = new int[arr.length];
		int index = 0;

		// Add all evens at first
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] % 2 == 0) {
				result[index] = arr[i];
				index++;
			}
		}
		// Add all odds at first
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] % 2 != 0) {
				result[index] = arr[i];
				index++;
			}
		}

		for (int array : result) {
			System.out.print(array + " ");
		}
	}
}
