package tcs;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FindUniqueWordsInGivenString {
	public static void main(String[] args) {
		String s = "Java is fun and Java is powerful";

		List<String> uniqueWords = Arrays.stream(s.split(" ")).map(String::toLowerCase).distinct().sorted()
				.collect(Collectors.toList());
		System.out.println(uniqueWords);
	}

}
