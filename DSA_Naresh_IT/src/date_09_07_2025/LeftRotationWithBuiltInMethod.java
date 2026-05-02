package date_09_07_2025;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LeftRotationWithBuiltInMethod {
	public static void main(String[] args) {
		int[] arr = { 1, 2, 3, 4, 5 };
		int d = 2;

		List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());

		leftRotate(list, d);
		System.out.println(list);
	}

	static void leftRotate(List<Integer> list, int d) {

		int n = list.size();

		d = d % n;

		Collections.reverse(list.subList(0, d));
		Collections.reverse(list.subList(d, n));
		Collections.reverse(list.subList(0, n));
	}

}
