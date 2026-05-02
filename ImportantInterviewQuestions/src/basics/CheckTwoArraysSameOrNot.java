package basics;

import java.util.Arrays;

public class CheckTwoArraysSameOrNot {
	public static void main(String[] args) {
		int[] a = {1, 2, 3, 4};
		int[] b = {4, 2, 3, 1};

		if (isTwoArraysSame(a, b)) {
			System.out.println(Arrays.toString(a) + " and " + Arrays.toString(b)
					+ " are same");
		} else {
			System.out.println("Both arrays are not same");
		}
	}

	private static boolean isTwoArraysSame(int[] a, int[] b) {
		if (a.length != b.length) {
			return false;
		}
		Arrays.sort(a);
		Arrays.sort(b);
		int length = a.length;

		for (int i = 0; i < length; i++) {
			if (a[i] != b[i]) {
				return false;
			}
		}
		return true;
	}
}
