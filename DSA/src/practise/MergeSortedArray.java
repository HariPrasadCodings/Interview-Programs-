package practise;

import java.util.Arrays;

public class MergeSortedArray {
	public static void main(String[] args) {
		int[] a1 = { 1, 2, 3, 0, 0, 0 };
		int[] a2 = { 4, 5, 6 };
		int m = 3;
		int n = a2.length;

		merge(a1, m, a2, n);
		System.out.println(Arrays.toString(a1));
	}

	private static void merge(int[] a1, int m, int[] a2, int n) {
		int i = m - 1;
		int j = n - 1;
		int k = m + n - 1;

		while (i >= 0 && j >= 0) {
			if (a1[i] > a2[j]) {
				a1[k--] = a1[i--];
			} else {
				a1[k--] = a2[j--];
			}
		}

		while (j >= 0) {
			a1[k--] = a2[j--];
		}
	}

}
