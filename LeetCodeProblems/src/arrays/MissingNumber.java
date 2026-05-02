package arrays;

// Problem number is : 268

public class MissingNumber {
	public static void main(String[] args) {
		int[] arr = {3, 0, 1};
		int originalSum = 3 * (3 + 1) / 2;
		int sum = 0;

		for (int i = 0; i < arr.length; i++) {
			sum = sum + arr[i];
		}
		System.out.println("Missing number: " + (originalSum - sum));
	}

}
