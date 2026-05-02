package arrays;

public class FindMissingNumberInArray {
	public static void main(String[] args) {
		int[] a1 = { 1, 2, 3, 5 }; // 4
		int[] a2 = { 8, 2, 4, 5, 3, 7, 1 }; // 6
		int[] a3 = { 1 }; // 2

		System.out.println(missingNumber(a1));
		System.out.println(missingNumber(a2));
		System.out.println(missingNumber(a3));
	}

	private static int missingNumber(int[] a1) {

		int sum = 0;
		int n = a1.length + 1;
		int totalSum = n * (n + 1) / 2;

		for (int i = 0; i < a1.length; i++) {
			sum = sum + a1[i];
		}

		return totalSum - sum;
	}

}
