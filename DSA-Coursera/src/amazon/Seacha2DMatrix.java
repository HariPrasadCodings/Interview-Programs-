package amazon;

public class Seacha2DMatrix {
	public static void main(String[] args) {
		int[][] matrix1 = { { 1, 3, 5, 7 }, { 10, 11, 16, 20 }, { 23, 30, 34, 60 } };
		int target1 = 30;
		System.out.println(searchMatrix(matrix1, target1));
	}

	private static boolean searchMatrix(int[][] matrix, int target) {

		if (matrix.length == 0 || matrix == null || matrix[0].length == 0) {
			return false;
		}

		int m = matrix.length; // number of rows
		int n = matrix[0].length; // number of columns
		int left = 0;
		int right = m * n - 1; // flatten length of the array

		while (left <= right) {
			int mid = left + (right - left) / 2;
			int midValue = matrix[mid / n][mid % n];
			if (midValue == target) {
				return true;
			} else if (midValue < target) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}

		return false;
	}

}
