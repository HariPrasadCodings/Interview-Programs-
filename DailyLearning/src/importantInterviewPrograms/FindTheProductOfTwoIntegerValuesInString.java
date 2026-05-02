package importantInterviewPrograms;

import java.math.BigInteger;

public class FindTheProductOfTwoIntegerValuesInString {
	public static void main(String[] args) {
		String num1 = "2";
		String num2 = "3";
		BigInteger integer = new BigInteger(num1);
		BigInteger integer2 = new BigInteger(num2);
		BigInteger product = integer.multiply(integer2);
		System.out.println("Product: " + product);

	}

}
