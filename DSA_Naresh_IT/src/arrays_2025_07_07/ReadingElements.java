package arrays_2025_07_07;

import java.util.Scanner;

public class ReadingElements {
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter total elements to read: ");
		int n = scanner.nextInt();
		System.out.println();
		int[] a = new int[n];

		// Reading elements
		for (int i = 0; i < n; i++) {
			a[i] = scanner.nextInt();
		}

		// Displaying elements
		for (int result : a) {
			System.out.print(result + " ");
		}

		scanner.close();

	}

}
