package interview;

public class FindMissingNumberInAnArray {
	public static void main(String[] args) {
		int[] arr = {1, 2, 3, 4, 6, 7};
		int n = 7;
		int totalSum = n * (n + 1) / 2;
		System.out.println(totalSum);
		int actualSum = 0;

		for (int i = 0; i < arr.length; i++) {
			actualSum = arr[i] + actualSum;
		}
		System.out.println("Missing Number is: " + (totalSum - actualSum));
	}

}
