package deloitte_april_2026.arrays;

public class MissingNumberInArray {
	public static void main(String[] args) {
		int[] arr = { 3, 0, 1 };

		int n = arr.length;
		int originalSum = n * (n + 1) / 2;
		int sum = 0;

		for (int num : arr) {
			sum = sum + num;
		}

		System.out.println("Missing Number: " + (originalSum - sum));
	}

}
