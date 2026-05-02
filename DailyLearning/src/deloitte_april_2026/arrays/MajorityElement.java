package deloitte_april_2026.arrays;

/**
 * I’m using Boyer-Moore Voting Algorithm which works in O(n) time and O(1)
 * space." It finds a candidate first, and then I validate it in a second pass
 * to ensure it’s actually the majority.
 */
public class MajorityElement {
	public static void main(String[] args) {
		int[] arr = { 2, 2, 1, 1, 1, 2, 2 };

		System.out.println(findMajorityElement(arr));
	}

	private static int findMajorityElement(int[] arr) {
		int candidate = 0;
		int count = 0;

		for (int num : arr) {
			if (count == 0) {
				candidate = num;
			}

			count = count + ((num == candidate) ? 1 : -1);
		}
		return candidate;
	}
}
