package deloitte_april_2026.arrays.practise;

public class MajorityElement {
	public static void main(String[] args) {
		int[] arr = { 2, 2, 1, 1, 1, 2, 2 };
		System.out.println("Majority Element: " + majority(arr));
	}

	static int majority(int[] arr) {
		int count = 0;
		int candidate = 0;

		for (int num : arr) {
			if (count == 0) {
				candidate = num;
			}

			count = count + ((num == candidate) ? 1 : -1);
		}
		return candidate;
	}

}
