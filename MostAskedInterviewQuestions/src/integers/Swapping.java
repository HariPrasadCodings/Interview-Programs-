package integers;

/**
 * Q #2) Write a Java Program to swap two numbers without using the third
 * variable.
 */

public class Swapping {
	public static void main(String[] args) {
		int a = 10;
		int b = 20;

		System.out.println("Before swapping: " + "a = " + a + " and " + "b = " + b);

		swapNumbers(a, b);
	}

	/**
	 * Explanation: This method uses arithmetic operations to swap two numbers
	 * without a temporary variable. It first adds the two numbers and stores the
	 * result in a, then subtracts b from the new a to recover the original a and
	 * assigns it to b, and finally subtracts the new b from the new a to recover
	 * the original b.
	 */
	private static void swapNumbers(int a, int b) {
		a = a + b; // 10 + 20 = 30
		b = a - b; // 30 - 20 = 10
		a = a - b; // 30 - 10 = 20

		System.out.println("After swapping: " + "a = " + a + " and " + "b = " + b);
	}

}
