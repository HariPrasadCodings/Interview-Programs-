package practise;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SumOfAllDigits {
	public static void main(String[] args) {
		int n = 1234567;
		int sum = 0;
		while (n != 0) {
			int digit = n % 10;
			sum = sum + digit;
			n = n / 10;
		}
		System.out.println(sum);

		int num = 5678;
		Integer collect = Stream.of(String.valueOf(num).split("")).collect(Collectors.summingInt(Integer::parseInt));
		System.out.println(collect);
	}

}
