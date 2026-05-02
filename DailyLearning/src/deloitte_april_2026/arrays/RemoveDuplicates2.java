package deloitte_april_2026.arrays;

/**
 * "I extend the two-pointer approach by allowing up to two occurrences using a
 * comparison with i-2."
 */
public class RemoveDuplicates2 {
	public static void main(String[] args) {
		int[] arr = { 0, 0, 1, 1, 1, 1, 2, 3, 3 };

		System.out.println(removeDuplicates(arr));
	}

	static int removeDuplicates(int[] arr) {
		if (arr.length <= 2)
			return arr.length;

		int i = 2;

		for (int j = 2; j < arr.length; j++) {
			if (arr[j] != arr[i - 2]) {
				arr[i] = arr[j];
				i++;
			}
		}

		return i;
	}

}

//k = 7
//[0,0,1,1,2,3,3]