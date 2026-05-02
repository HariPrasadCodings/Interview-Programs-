package practise;

public class FindSecondLowest {
	public static void main(String[] args) {
		int[] arr = { 9, 7, 3, 4, 5, 1, 10, 12 };
		System.out.println("Second Lowest Element is: " + findSecondLowest(arr));
	}

	static int findSecondLowest(int[] arr) {
		int min = arr[0];
		int secondLowest = Integer.MIN_VALUE;

		for (int i = 0; i < arr.length; i++) {
			if (arr[i] < min) {
				secondLowest = min;
				min = arr[i];
			} else if (arr[i] < secondLowest && arr[i] < min) {
				secondLowest = arr[i];
			}
		}
		return secondLowest;
	}

}
