package strings;

public class PrintStringBasedOnGivenNumberTimes {
	public static void main(String[] args) {
		String s = "Hari";
		String s1 = "Prasad";
		printNumberOfTimes(s, 5);
		printNumberOfTimes(s1, 3);
	}

	public static void printNumberOfTimes(String s, int n) {
		for (int i = 0; i < n; i++) {
			System.out.println(s);
		}
	}
}
