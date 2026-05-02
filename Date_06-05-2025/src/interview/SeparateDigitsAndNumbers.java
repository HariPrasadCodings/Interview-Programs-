package interview;

import java.util.Arrays;

public class SeparateDigitsAndNumbers {
	public static void main(String[] args) {
		String input = "ACB133DFH";
		StringBuilder letters = new StringBuilder();
		StringBuilder digits = new StringBuilder();

		for (char c : input.toCharArray()) {
			if (Character.isLetter(c)) {
				letters.append(c);
			} else if (Character.isDigit(c)) {
				digits.append(c);
			}
		}
		System.out.println("Letters: " + letters.toString() + " Digits: "
				+ digits.toString());
		
		 int[] arr = {2,4,5,6,7,8,23,45,67};
		 
		 int asInt = Arrays.stream(arr).max().getAsInt();
		 System.out.println(asInt);
		 
		 int min = Arrays.stream(arr).min().getAsInt();
		 System.out.println(min);
	}

}
