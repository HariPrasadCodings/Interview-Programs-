package date_13_06_2025;

import java.util.Scanner;
import java.util.stream.IntStream;

public class PrimeCheckWithJava8 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter any number");

		int num = scan.nextInt();

		boolean isPrime = num > 1
				&& IntStream.rangeClosed(2, (int) Math.sqrt(num))
						.allMatch(i -> num % i != 0);
		System.out.println(num
				+ (isPrime ? " is a Prime Number" : " is not a Prime number"));
		scan.close();
	}

}
