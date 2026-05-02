package reversing;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ReverseAnArray {
	public static void main(String[] args) {
		int[] arr = { 4, 6, 2, 1, 7, 8 };
		// reverseUsingTemp(arr);

		// reverseArrayUsingTwoPointers(arr);

		// reverseArrayBySwappingElements(arr);

		reverseArrayUsingInBuiltMethods(arr);
		//System.out.println(Arrays.toString(arr));

	}

	// Approach 1: Using Temporary Array
	/*
	 * Complexity Analysis: Time Complexity: O(n), Copying elements to a new array
	 * is a linear operation. Space Complexity: O(n), as we are using an extra array
	 * to store the reversed array.
	 */
	private static void reverseUsingTemp(int[] arr) {
		int n = arr.length;
		int[] temp = new int[n];

		// Copy Elements in reverse order into temp
		for (int i = 0; i < n; i++) {
			temp[i] = arr[n - i - 1];
		}

		// Copy back into original array
		for (int i = 0; i < n; i++) {
			arr[i] = temp[i];
		}
	}

	// Approach 2: Using Two pointers
	/*
	 * Complexity Analysis: Time Complexity: O(n), as we are visiting each element
	 * exactly once. Space Complexity: O(1)
	 */
	private static void reverseArrayUsingTwoPointers(int[] arr) {
		int start = 0;
		int end = arr.length - 1;

		while (start < end) {
			int temp = arr[start];
			arr[start] = arr[end];
			arr[end] = temp;

			start++;
			end--;
		}
	}

	// Approach 3. By Swapping Elements
	/*
	 * Complexity Analysis: Time Complexity: O(n), the loop runs through half of the
	 * array, so it’s linear with respect to the array size. Space Complexity: O(1),
	 * no extra space is required; therefore, we are reversing the array in-place.
	 */
	private static void reverseArrayBySwappingElements(int[] arr) {
		int n = arr.length;
		for (int i = 0; i < n / 2; i++) {
			int temp = arr[i];
			arr[i] = arr[n - i - 1];
			arr[n - i - 1] = temp;
		}
	}

	// Approach 4. Using Inbuilt Methods
	/*
	 * Time Complexity: O(n), the reverse method has linear time complexity.
	 * Auxiliary Space: O(1) Additional space is not used to store the reversed
	 * array, as the in-built array method swaps the values in-place.
	 */
	private static void reverseArrayUsingInBuiltMethods(int[] arr) {
		// 1. Convert the array to a List.
		List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());

		// 2. Use Collections.reverse().
		Collections.reverse(list);

		// Optionally convert back to an array.
		System.out.println(list);
	}
}
