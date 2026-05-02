package java8;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FindTheMostFrequencyWord {
	public static void main(String[] args) {
		String sentence = "java is great java is powerful java streams are great";
		String mostFrequent = Arrays.stream(sentence.split(" "))
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream()
				.max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse("Not Found");
		System.out.println(mostFrequent);
	}

}
