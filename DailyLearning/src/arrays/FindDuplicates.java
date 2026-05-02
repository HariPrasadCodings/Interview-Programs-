package arrays;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class FindDuplicates {
	public static void main(String[] args) {
		int[] arr = { 1, 2, 3, 3, 5 };

		Set<Integer> seen = new HashSet<>();
		int[] result = Arrays.stream(arr).filter(n -> !seen.add(n)).toArray();
		System.out.println(Arrays.toString(result));
	}

}
