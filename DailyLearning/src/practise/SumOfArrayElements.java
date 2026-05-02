package practise;

import java.util.Arrays;

public class SumOfArrayElements {
	public static void main(String[] args) {
		int[] input = { 2, 3, 4, 5, 7 };
		int sum = Arrays.stream(input).sum();
		System.out.println(sum);

		int n = 12345;

		int total = 0;
		while (n != 0) {
			int digit = n % 10;
			total = total * 10 + digit; // 54321
			n = n / 10;
		}

		System.out.println("Reversed number is: " + total);

		int n1 = 1234;
		int s = 0;
		while (n1 != 0) {
			int digit = n1 % 10;
			s = digit + s;
			n1 = n1 / 10;
		}

		System.out.println(s);
	}
}
