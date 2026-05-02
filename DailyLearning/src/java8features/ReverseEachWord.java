package java8features;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ReverseEachWord {
	public static void main(String[] args) {
		String input = "Hari Prasad Kathi";

		String reverseEachWord = Arrays.stream(input.split(" ")).map(word -> new StringBuffer(word).reverse())
				.collect(Collectors.joining(" "));
		System.out.println(reverseEachWord);
	}

}
