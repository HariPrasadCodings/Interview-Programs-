package searching_15_07_2025;

public class LinearSearch {
	public static void main(String[] args) {
		int[] arr = { 3, 5, 1, 2, 8, 9 };
		int target = 8;

		System.out.println(linearSearch(arr, target)); // index at 4
		System.out.println(linearSeachWithRecursion(arr, 0, target));
	}

	static int linearSearch(int[] arr, int target) {

		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == target) {
				return i;
			}
		}

		return -1;

	}

	static int linearSeachWithRecursion(int[] arr, int index, int target) {
		if (index >= arr.length) {
			return -1; // If target not found
		}
		if (arr[index] == target) {
			return index; // if target found return that index
		}

		return linearSeachWithRecursion(arr, index + 1, target);
	}
}
