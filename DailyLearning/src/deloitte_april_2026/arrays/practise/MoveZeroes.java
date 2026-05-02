package deloitte_april_2026.arrays.practise;

import java.util.Arrays;

public class MoveZeroes {
	public static void main(String[] args) {
		int[] arr = { 1, 0, 2, 3, 0, 4, 0, 5, 0, 6 };

//		int[] moveZeros = moveZeros(arr);
//		System.out.println(Arrays.toString(moveZeros));

		move(arr);
		System.out.println(Arrays.toString(arr));
	}

	static int[] moveZeros(int[] arr) {
		int[] result = new int[arr.length];

		int count = 0;
		for (int num : arr) {
			if (num != 0) {
				result[count++] = num;
			}
		}

		while (count < arr.length) {
			result[count++] = 0;
		}
		return result;
	}

	// Approach:2
	static void move(int[] nums) {
		int index = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] != 0) {
				int temp = nums[index];
				nums[index] = nums[i];
				nums[i] = temp;

				index++;
			}
		}
	}

}
