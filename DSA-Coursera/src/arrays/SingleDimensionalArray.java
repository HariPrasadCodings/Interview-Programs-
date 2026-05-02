package arrays;

public class SingleDimensionalArray {
	public static void main(String[] args) {
		// Declaring an array
		// int[] marks;
		// marks = new int[5];
		// OR
		// int[] marks = new int[5];

		// Intializing an array
		// int[] marks = new int[] { 10, 20, 30, 40, 50 };

		// Declare and assign
		int[] marks = new int[5];
		marks[0] = 10;
		marks[1] = 20;
		marks[2] = 30;
		marks[3] = 40;
		marks[4] = 50;
		System.out.println("********Traditional for loop**********");
		for (int i = 0; i < marks.length; i++) {
			System.out.print(marks[i] + " ");
		}
		System.out.println("\n********for-each loop*************");
		for (int result : marks) {
			System.out.print(result + " ");
		}
	}

}
