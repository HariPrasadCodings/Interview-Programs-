package deloitte;

import java.util.stream.IntStream;

public class NumberToWords {
	private static final String[] belowTwenty = {"", "One", "Two", "Three",
			"Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven",
			"Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen",
			"Eighteen", "Ninteen"};
	private static final String[] tens = {"", "Ten", "Twenty", "Thirty",
			"Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
	private static final String[] thousands = {"", "Thousand", "Million",
			"Billion"};

	public static void main(String[] args) {
		String[] input = {"1128", "0501", "10005", "0051", "0198"};
		IntStream.range(0, input.length)
				.mapToObj(i -> input[i] + " : " + numberToWords(input[i]))
				.forEach(System.out::println);
	}
	public static String numberToWords(String numStr) {
		// Handle the case of "0000" or empty input
		if (numStr == null || numStr.isEmpty()
				|| numStr.replaceAll("0", "").isEmpty()) {
			return "Zero";
		}

		// Validate and parse the input
		int[] numberHolder = {
				Integer.parseInt(numStr.replaceFirst("^0+(?!$)", ""))};

		// Convert to words
		return IntStream.range(0, thousands.length)
				.filter(i -> numberHolder[0] > 0).mapToObj(i -> {
					int part = numberHolder[0] % 1000;
					numberHolder[0] = numberHolder[0] / 1000;
					return part > 0 ? helper(part) + thousands[i] + " " : "";
				}).reduce("", (a, b) -> b + a) // Reverse and concatenate
				.trim();
	}

	private static String helper(int num) {
		if (num == 0)
			return "";
		if (num < 20)
			return belowTwenty[num] + " ";
		if (num < 100)
			return tens[num / 10] + " " + belowTwenty[num % 10] + " ";
		return belowTwenty[num / 100] + " Hundred " + helper(num % 100);
	}
	


}
