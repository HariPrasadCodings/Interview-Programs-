package date_04_07_2025;

public class FindSumOfAllDigitsOfNumber {
	public static void main(String[] args) {
		int n = 123456789;
		// int sum = 0;

		// using java 7
		// while (n > 0) {
		// sum = sum + n % 10;
		// n = n / 10;
		// }
		//
		// System.out.println("Sum: " + sum);

		// using java 8

		int sum2 = String.valueOf(n).chars()
				.peek(ch -> System.out.println("Chars value output: " + ch))
				.map(Character::getNumericValue)
				.peek(ch -> System.out.println("Numeric value output: " + ch))
				.sum();
		System.out.println(sum2);
	}

}
