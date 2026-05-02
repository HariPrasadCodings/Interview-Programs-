package arrays;

public class MultiDimensionalArray {
	public static void main(String[] args) {
		int[][] arr = new int[3][3];

		int[][] marks = { { 55, 65, 75, 85, 95 }, { 67, 77, 87, 97, 57 }, { 78, 68, 58, 98, 88 } };

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 5; j++) {
				System.out.print(marks[i][j] + "\t");
			}
			System.out.println();
		}
	}

}
