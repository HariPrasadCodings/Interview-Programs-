package interview;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortListOfStringInIncreasingOrderOfTheirLength {
	public static void main(String[] args) {
		List<String> strings = Arrays.asList("HarshaVardhan", "Santhosh", "hari", "ravi", "ramesh", "sudheer",
				"subash");
		strings.stream().sorted(Comparator.comparing(String::length)).collect(Collectors.toList())
				.forEach(result -> System.out.println(result));
	}

}
