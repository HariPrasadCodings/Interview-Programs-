package practise;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class PrintLastElementInArray {
	public static void main(String[] args) {
		List<String> listOfStrings = Arrays.asList("One", "Two", "Three", "Four", "Five", "Six");
		Optional<String> string = listOfStrings.stream().skip(listOfStrings.size() - 1).findFirst();
		string.ifPresent(System.out::println);
	}

}
