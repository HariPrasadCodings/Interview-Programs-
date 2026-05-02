package interview;

import java.util.concurrent.ThreadLocalRandom;

public class RandomNumber {
	public static void main(String[] args) {
		int min = 1;
		int max = 100;
		System.out.println("Random value between " + min + " and " + max + ": "
				+ getRandomValue(min, max));

	}

	private static int getRandomValue(int min, int max) {
		return ThreadLocalRandom.current().nextInt(min, max+1);
	}
}
