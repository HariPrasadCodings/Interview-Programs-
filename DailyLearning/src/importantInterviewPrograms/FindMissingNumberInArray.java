package importantInterviewPrograms;

public class FindMissingNumberInArray {
	public static void main(String[] args) {
		int[] a = {1, 2, 3, 5};
		int n = a.length + 1;
		int originalSum = n * (n + 1) / 2; // 10
		int sum = 0;

		for (int i = 0; i < a.length; i++) {
			sum = sum + a[i]; // 11
		}
		System.out.println("Missing Number: " + (originalSum - sum));
	}

}
