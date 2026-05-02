package importantInterviewPrograms;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FindTheOccuranceOfacharacterinString {
	public static void main(String[] args) {
		String s = "FollowCodeYatra";
		// approach: 1
		long count = Arrays.stream(s.split("")).filter(x -> x.contains("a"))
				.count();
		System.out.println("Frequency of character a is " + count);

		// approach: 2
		long count2 = Arrays
				.stream(s.split("")).collect(Collectors
						.groupingBy(Function.identity(), Collectors.counting()))
				.getOrDefault("a", 0l);
		System.out.println(count2);
		// approach: 3
		long count3 = s.chars().filter(ch -> ch == 'a').count();
		System.out.println(count3);
	}

}
