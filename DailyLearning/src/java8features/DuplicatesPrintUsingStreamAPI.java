package java8features;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DuplicatesPrintUsingStreamAPI {
	public static void main(String[] args) {
		String s = "welcometothejavaworld"; // w = 2; e = 3; o = 3; t = 2; a = 2

		Map<String, Long> eachWordOccurance = Arrays.stream(s.split(""))
				.collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));
		System.out.println(eachWordOccurance);
		
		Map<String, Integer> valueCount = new HashMap<>();
		String[]  words = s.split("");
		for(String count: words) {
			valueCount.put(count, valueCount.getOrDefault(count, 0)+1);
		}
		
		
		
		

		// op: {w=2, e=3, l=2, c=1, o=3, m=1, t=2, h=1, j=1, a=2, v=1, r=1, d=1}

		List<String> duplicates = eachWordOccurance.entrySet().stream().filter(x -> x.getValue() > 1)
				.map(Map.Entry::getKey).collect(Collectors.toList());
		System.out.println(duplicates);
		// [a, t, e, w, l, o]

		String nonrepeatElement = eachWordOccurance.entrySet().stream().filter(x -> x.getValue() == 1)
				.map(Map.Entry::getKey).findFirst().get();
		System.out.println("First non repeat element: " + nonrepeatElement);
		
		String[] tools = {"Java","Spring","SpringBoot","Microservices","Hibernate","WebServices"};
		

	}

}
