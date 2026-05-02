package exceptions;

/**Question: Can you have a try block with only a finally block and no catch.
 * 
 * Yes, It is possible
*/

/**
 * Catch block is not mandatory when you have try with finally block, But to
 * catch the exceptions keep the catch block. finally block is meant for cleanup
 * the resources.
 */

public class TryBlockExample {

	private static void test() {
		try {
			System.out.println("Inside try block..");
		} finally {
			System.out.println("Inside finally block");
		}
	}

	public static void main(String[] args) {
		test();
	}

}
