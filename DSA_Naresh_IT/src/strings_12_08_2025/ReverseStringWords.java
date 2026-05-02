package strings_12_08_2025;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ReverseStringWords {
	public static void main(String[] args) {
		String s = "the sky is blue";

		System.out.println(reverse(s));

		String s1 = "Hari is a good boy";

		System.out.println(reverseUsingTwoPointers(s1));
	}

	// Approach: 1 Using Built-in functions Split, reverse, join
	// TC: O(n) : split, reverse, and join each require traversing the string
	// SC: O(n) extra space for the word array and result string
	static String reverse(String s) {
		List<String> words = Arrays.asList(s.trim().split("\\s+"));
		Collections.reverse(words);
		return String.join(" ", words);
	}

	// Approach : 2 Manual -in place (Two Pointers, without split)
	static String reverseUsingTwoPointers(String s) {
		char[] arr = s.trim().toCharArray();
		System.out.println(Arrays.toString(arr));
		// Step1: Reverse the whole string
		reverseString(arr, 0, arr.length - 1);

		System.out.println("After Reversing: " + Arrays.toString(arr));

		int start = 0;

		for (int end = 0; end <= arr.length; end++) {
			if (end == arr.length || arr[end] == ' ') {
				reverseString(arr, start, end-1);
				start = end + 1;
			}
		}

		// Step 2: Remove extra spaces
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != ' ' || (builder.length() > 0 && builder.charAt(builder.length() - 1) != ' ')) {
				builder.append(arr[i]);
			}
		}
		return builder.toString();
	}

	static void reverseString(char[] arr, int left, int right) {
		while (left < right) {
			char temp = arr[left];
			arr[left] = arr[right];
			arr[right] = temp;
		}
	}

}
