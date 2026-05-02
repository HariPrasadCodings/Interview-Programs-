package exceptions;

/**
 * Question: What happens if a finally block modifies a value returned by the
 * try block.
 * 
 * Answer: No The return in try captures the value of x (0) before finally
 * executes. The final return value remains 0 if x is changed.
 */
public class Modifyingreturnvalueoftryblock {

	private static int test() {
		int x = 0;
		try {
			return x;
		} finally {
			x = 2;
			return x;
		}
	}

	public static void main(String[] args) {
		System.out.println(test());
	}

}
