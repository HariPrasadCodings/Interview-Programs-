package date_04_07_2025;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PartitionStrings {
	public static void main(String[] args) {
		List<String> strings = Arrays.asList("Java", "C", "Python", "Go",
				"Ruby");
		Map<Boolean, List<String>> partitioningBy = strings.stream()
				.collect(Collectors.partitioningBy(s -> s.length() > 3));
		System.out.println("More than 3: " + partitioningBy.get(true));
		System.out.println("Less than or 3: " + partitioningBy.get(false));
	}

}
