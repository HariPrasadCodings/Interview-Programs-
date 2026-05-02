package interview_practise;

import java.util.List;

public class ReverseANumber {
	public static void main(String[] args) {
		int n = 12345;

		int reversed = 0;

		while (n > 0) {
			int digit = n % 10;
			reversed = reversed * 10 + digit;
			n = n / 10;
		}

		System.out.println("Reversed number is: " + reversed);

		// using java 8 approach
		int n1 = 6789;

		List<Integer> reversedNumber = new StringBuilder(String.valueOf(n1))
				.reverse().chars().mapToObj(Character::getNumericValue)
				.toList();
		System.out.println(reversedNumber);

	}

}
