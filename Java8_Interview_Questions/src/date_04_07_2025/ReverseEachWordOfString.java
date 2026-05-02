package date_04_07_2025;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ReverseEachWordOfString {
	public static void main(String[] args) {
		String s = "hello hari prasad";

		String result = Arrays.stream(s.split(" "))
				.map(str -> new StringBuilder(str).reverse().toString())
				.collect(Collectors.joining(" "));
		System.out.println(result);
	}

}
