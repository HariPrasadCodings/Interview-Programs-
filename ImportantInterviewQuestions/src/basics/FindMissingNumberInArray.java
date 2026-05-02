package basics;

public class FindMissingNumberInArray {
	public static void main(String[] args) {

		int[] arr = {1, 2, 3, 5};
		int originalSum = 5 * (5 + 1) / 2;
		int sum = 0;

		for (int i = 0; i < arr.length; i++) {
			sum = sum + arr[i];
		}
		System.out.println("Missing Number: " + (originalSum - sum));
	}

}
