package exceptions;

/**
 * Answer : it returns 2. The finally block overrides the try blck's response.
 */
public class FindOutputOfGivenCode {

	@SuppressWarnings("finally")
	private static int test() {
		try {
			return 1;
		} finally {
			return 2;
		}
	}

	public static void main(String[] args) {
		System.out.println("Output is: " + test());
	}

}
