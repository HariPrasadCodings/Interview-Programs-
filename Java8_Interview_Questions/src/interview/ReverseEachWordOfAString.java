package interview;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ReverseEachWordOfAString {
	public static void main(String[] args) {
		String input = "hello hai how are you";

		String reversedEachWord = Arrays.stream(input.split(" ")).map(string -> new StringBuffer(string).reverse())
				.collect(Collectors.joining(" "));
		System.out.print(reversedEachWord);
	}

}
