package arrays;

public class FindSingleNumber {
	public static void main(String[] args) {
		int[] arr = {2, 3, 4, 2, 9, 3, 4, 5, 5};
		System.out.println((singleNumber(arr)));
	}

	// private static int singleNumber(int[] nums) {
	// Map<Integer, Integer> map = new HashMap<>();
	//
	// for (int count : nums) {
	// map.put(count, map.getOrDefault(count, 0) + 1);
	// }
	//
	// for (Entry<Integer, Integer> entry : map.entrySet()) {
	// if (map.get(entry.getKey()) == 1) {
	// return entry.getKey();
	// }
	// }
	// return -1;
	// }

	public static int singleNumber(int[] nums) {
		int single = nums[0];

		for (int i = 1; i < nums.length; i++) {
			single = single ^ nums[i];
		}
		return single;
	}

}
