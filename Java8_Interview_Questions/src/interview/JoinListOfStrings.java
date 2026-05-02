package interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JoinListOfStrings {
	public static void main(String[] args) {
		List<String> strings = new ArrayList<>(Arrays.asList("hari", "ravi", "ramesh", "sudheer"));
		String join = strings.stream().collect(Collectors.joining("-", "_", "/"));
		System.out.println(join);
	}

}
