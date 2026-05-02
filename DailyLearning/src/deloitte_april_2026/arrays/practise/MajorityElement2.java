package deloitte_april_2026.arrays.practise;

import java.util.ArrayList;

public class MajorityElement2 {
	public static void main(String[] args) {
		int[] arr = { 1, 2, 3, 1, 2, 1, 2, 1 };
		
		System.out.println(majorityElement(arr));
	}

	static ArrayList<Integer> majorityElement(int[] arr) {
		int count1 = 0;
		int count2 = 0;
		int candidate1 = 0;
		int candidate2 = 0;

		for (int num : arr) {
			if (num == candidate1) {
				count1++;
			} else if (num == candidate2) {
				count2++;
			} else if (count1 == 0) {
				candidate1 = num;
				count1 = 1;
			} else if (count2 == 0) {
				candidate2 = num;
				count2 = 1;
			} else {
				count1--;
				count2--;
			}
		}

		count1 = 0;
		count2 = 0;

		for (int num : arr) {
			if (num == candidate1) {
				count1++;
			} else if (num == candidate2) {
				count2++;
			}
		}

		int n = arr.length;
		ArrayList<Integer> result = new ArrayList<>();
		if (count1 > n / 3)
			result.add(candidate1);
		if (count2 > n / 3)
			result.add(candidate2);

		return result;
	}

}
