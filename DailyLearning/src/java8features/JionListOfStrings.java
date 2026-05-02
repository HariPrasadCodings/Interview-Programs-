package java8features;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JionListOfStrings {
	public static void main(String[] args) {
		List<String> strings = Arrays.asList("Java", "Spring", "Hibernate", "SpringBoot", "WebServices");
		String join = strings.stream().collect(Collectors.joining("-", "@", "_"));
		System.out.println(join);
	}

}
