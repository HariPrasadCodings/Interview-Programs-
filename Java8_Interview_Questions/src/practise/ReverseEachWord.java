package practise;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ReverseEachWord {
	public static void main(String[] args) {
		String s = "hari is a java developer";

		String reverseeachWord = Arrays.stream(s.split(" ")).map(word -> new StringBuilder(word).reverse())
				.collect(Collectors.joining(" "));
		System.out.println(reverseeachWord);
	}

}
