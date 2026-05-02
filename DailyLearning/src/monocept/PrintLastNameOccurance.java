package monocept;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class PrintLastNameOccurance {
	public static void main(String[] args) {
		String[] names = { "swathik nitturi", "chandu mark", "vemula prakashs" };

		Map<String, Long> collect = Arrays.stream(names).map(name -> name.split(" ")[1])
				.collect(Collectors.groupingBy(e -> e, Collectors.counting()));

		String result = collect.entrySet().stream().map(entry -> entry.getKey() + "-" + entry.getValue())
				.collect(Collectors.joining(","));
		System.out.println(result);
	}

}
