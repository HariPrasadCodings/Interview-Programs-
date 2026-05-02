package java_interview_questions;

import java.util.Arrays;

public class CheckTwoArraysSameOrNot {
	public static void main(String[] args) {
		int[] a = { 1, 2, 3, 4, 5 };
		int[] b = { 3, 4, 5, 2, 6 };
		isTwoArraysSame(a, b);
		if (isTwoArraysSame(a, b)) {
			System.out.println("Both arrays are same");
		} else {
			System.out.println("Both arrays are not same");
		}
	}

	private static boolean isTwoArraysSame(int[] a, int[] b) {

		int l1 = a.length;
		int l2 = b.length;
		if (l1 != l2) {
			return false;
		} else {
			Arrays.sort(a);
			Arrays.sort(b);
			for (int i = 0; i < l1; i++) {
				if (a[i] != b[i]) {
					return false;
				}
			}
		}
		return true;
	}

}
