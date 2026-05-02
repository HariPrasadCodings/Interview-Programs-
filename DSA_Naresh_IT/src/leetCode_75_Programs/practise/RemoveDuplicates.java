package leetCode_75_Programs.practise;

public class RemoveDuplicates {
	public static void main(String[] args) {
		int[] arr = { 0, 0, 0, 1, 1, 1, 2, 2 };
		System.out.println(removeDuplicates(arr));
	}

	static int removeDuplicates(int[] arr) {
		int slow = 0;

		for (int fast = 1; fast < arr.length; fast++) {
			if (arr[fast] != arr[slow]) {
				slow++;
				arr[slow] = arr[fast];
			}
		}
		return slow + 1;
	}
}
