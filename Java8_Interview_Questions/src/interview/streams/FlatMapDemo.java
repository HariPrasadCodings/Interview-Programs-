package interview.streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FlatMapDemo {
	public static void main(String[] args) {
		List<List<Integer>> lists = Arrays.asList(Arrays.asList(1, 2, 3, 4, 5, 6), Arrays.asList(7, 8, 9, 10, 12, 15),
				Arrays.asList(18, 20, 19, 21, 11, 13));
		List<Integer> collect = lists.stream().flatMap(n -> n.stream()).sorted().distinct()
				.collect(Collectors.toList());
		System.out.println(collect);
		
		
	}

}
