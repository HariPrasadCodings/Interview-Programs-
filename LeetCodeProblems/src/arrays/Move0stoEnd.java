package arrays;

import java.util.Arrays;

public class Move0stoEnd {
	public static void main(String[] args) {
		int[] arr = {0, 1, 0, 3, 12};

		moveZeroes(arr);
		System.out.println(Arrays.toString(arr));
	}

	private static void moveZeroes(int[] nums) {
		int[] nonZeroes = Arrays.stream(nums).filter(num -> num != 0).toArray();

		System.arraycopy(nonZeroes, 0, nums, 0, nonZeroes.length);

		Arrays.fill(nums, nonZeroes.length, nums.length, 0);
	}

}
