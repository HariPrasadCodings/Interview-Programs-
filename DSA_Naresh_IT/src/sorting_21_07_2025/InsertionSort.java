package sorting_21_07_2025;

public class InsertionSort {
	public static void main(String[] args) {
		int[] arr = { 15, 20, 10, 30, 50, 18, 5, 45 };
		int n = arr.length;

		System.out.println("Before insertion sort: ");
		for (int i = 0; i < n; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
		insertionSort(arr, n);
	}

	private static void insertionSort(int[] arr, int n) {
		for (int i = 0; i < n - 1; i++) {
			int j = i;

			while (j > 0 && arr[j - 1] > arr[j]) {
				int temp = arr[j - 1];
				arr[j - 1] = arr[j];
				arr[j] = temp;
				j--;
			}
		}

		System.out.println("After insertion sort: ");

		for (int i = 0; i < n; i++) {
			System.out.print(arr[i] + " ");
		}
	}

}
