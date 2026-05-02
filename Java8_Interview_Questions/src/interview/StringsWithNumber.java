package interview;

import java.util.Arrays;
import java.util.List;

public class StringsWithNumber {
	public static void main(String[] args) {
		List<String> strings = Arrays.asList("har1", "rav2", "mani3", "siva", "2kumar");
		strings.stream().filter(str -> Character.isDigit(str.charAt(0))).forEach(System.out::println);
	}

}
