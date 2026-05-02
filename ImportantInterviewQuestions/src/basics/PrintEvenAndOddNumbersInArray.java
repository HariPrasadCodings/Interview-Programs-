package basics;

public class PrintEvenAndOddNumbersInArray {
	public static void main(String[] args) {
		int[] arr = {1, 2, 3, 4, 5, 6};
		printEvenAndOdd(arr);
	}

	private static void printEvenAndOdd(int[] arr) {
		System.out.println("Even Numbers are:  ");
		for (int num : arr) {
			if (num % 2 == 0) {
				System.out.print(num + " ");
			}
		}
		System.out.println(); // For line break
		System.out.println("Odd Numbers are:  ");
		for (int num : arr) {
			if (num % 2 != 0) {
				System.out.print(num + " ");
			}
		}
	}

}
