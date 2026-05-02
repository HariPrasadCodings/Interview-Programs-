package arrays;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//Problem number is : 448

public class DisapperElementsInArray {

	public static void main(String[] args) {
		// Example 1:
		//
		// Input: nums = [4,3,2,7,8,2,3,1]
		// Output: [5,6]
		// Example 2:
		//
		// Input: nums = [1,1]
		// Output: [2]

		int[] arr1 = {4, 3, 2, 7, 8, 2, 3, 1};
		int[] arr2 = {1, 1};
		System.out.println(findDisappearedNumbers(arr1));
		System.out.println(findDisappearedNumbers(arr2));

	}

	private static List<Integer> findDisappearedNumbers(int[] arr) {
		int n = arr.length;
		Set<Integer> set = new HashSet<>();
		List<Integer> result = new ArrayList<>();

		for (int num : arr) {
			set.add(num);
		}

		for (int i = 1; i <= n; i++) {
			if (!set.contains(i)) {
				result.add(i);
			}
		}
		return result;
	}

}
