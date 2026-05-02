package hcl;

import java.util.Scanner;

public class PowerOfIntegers {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int base = scanner.nextInt();
		int power = scanner.nextInt();
		int pow = power(base, power);

		// easiest way
		double powers = Math.pow(base, power);
		System.out.println(powers);

		System.out.println(pow);
		scanner.close();

	}

	private static int power(int b, int p) {
		int power = 1;
		for (int i = 1; i <= p; i++) {
			power = power * b; // 1 * 2 = 2; 2 * 2 = 4; 4 * 2 = 8
		}
		return power;
	}

}
