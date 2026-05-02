package java_interview_questions;

import java.util.Arrays;

public class EvenLeftOddRight {
	public static void main(String[] args) {
		System.out.println("=====Before modifying ====");
		int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		
		System.out.println(Arrays.toString(arr));
		
		System.out.println("=====After modifying ====");
		evenleftAndOddRight(arr);
	}

	private static void evenleftAndOddRight(int[] arr) {
		int[] newArray = new int[arr.length];
		int index = 0;

		for (int i = 0; i < arr.length; i++) {
			if (arr[i] % 2 == 0) {
				newArray[index] = arr[i];
				index++;
			}
		}

		for (int i = 0; i < arr.length; i++) {
			if (arr[i] % 2 != 0) {
				newArray[index] = arr[i];
				index++;
			}
		}

		for (int result : newArray) {
			System.out.print(result + " ");
		}
	}

}
