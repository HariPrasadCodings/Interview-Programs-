package arrays_14_07_2025;

public class PascalsTriangle {
	public static void main(String[] args) {

	}

	// Approach 1:

	// Approach 2: using NCR

	// Helper method to find factorial
	static int factorial(int num) {
		int result = 1;
		for (int i = 2; i <= num; i++) {
			result = result * i;
		}
		return result;
	}
	
	// NCR function
	static int ncr(int n, int r) {
		return factorial(n) / (factorial(r) * factorial(n - r));
	}

}
