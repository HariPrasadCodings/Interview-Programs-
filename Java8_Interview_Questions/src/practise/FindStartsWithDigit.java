package practise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindStartsWithDigit {
	public static void main(String[] args) {
		// List<String> listOfStrings = Arrays.asList("One", "2wo", "3hree",
		// "Four", "5ive", "Six");
		//
		// listOfStrings.stream().filter(ch -> Character.isDigit(ch.charAt(0)))
		// .forEach(System.out::println);

		List<String> strings = new ArrayList<>(
				Arrays.asList("1ne", "two", "three", "4our"));
		strings.stream().filter(ch -> Character.isDigit(ch.charAt(0)))
				.forEach(result -> System.out.println(result + " "));
	}

}
