package date_04_07_2025;

import java.util.Arrays;
import java.util.List;

public class AverageStringLength {
	public static void main(String[] args) {
		List<String> strings = Arrays.asList("Java", "Python", "Spring",
				"SpringBoot");
		double averageLength = strings.stream().mapToInt(String::length)
				.average().orElse(0.0);
		System.out.println("AverageString length is: " + averageLength);
	}

}
