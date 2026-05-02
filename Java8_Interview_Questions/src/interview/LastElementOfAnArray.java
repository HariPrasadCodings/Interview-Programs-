package interview;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class LastElementOfAnArray {
	public static void main(String[] args) {
		int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};

		List<Integer> list = Arrays.stream(arr).boxed().toList();
		Optional<Integer> lastElement = list.stream().skip(list.size() - 1)
				.findFirst();
		if (lastElement.isPresent()) {
			System.out.println(lastElement.get());
		}
	}

}
