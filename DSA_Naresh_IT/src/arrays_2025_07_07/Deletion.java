package arrays_2025_07_07;

public class Deletion {
	public static void main(String[] args) {
		int[] arr = { 10, 20, 30, 40, 50 };

		int position = 2;
		int n = arr.length;

		for (int i = position; i < n - 1; i++) {
			arr[i] = arr[i + 1];
		}
		n = n - 1;

		for (int i = 0; i < n; i++) {
			System.out.print(arr[i] + " ");
		}

	}

}
