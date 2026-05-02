package importantInterviewPrograms;

import java.util.Arrays;

public class CheckTwoArraysSameOrNot {
	public static void main(String[] args) {
		int[] a = {1, 2, 3, 4};
		int[] b = {1, 2, 9, 4};

		boolean isArraysSame = isBothArraysSame(a, b);
		if (isArraysSame) {
			System.out.println("Both Arrays are same");
		} else {
			System.out.println("Both Arrays are not same");
		}
	}

	private static boolean isBothArraysSame(int[] a, int[] b) {
		int l1 = a.length;
		int l2 = b.length;
		if (l1 != l2) {
			return false;
		} else {
			for (int i = 0; i < l1; i++) {
				Arrays.sort(a);
				Arrays.sort(b);
				if (a[i] != b[i]) {
					return false;
				}
			}
		}
		return true;
	}

}
