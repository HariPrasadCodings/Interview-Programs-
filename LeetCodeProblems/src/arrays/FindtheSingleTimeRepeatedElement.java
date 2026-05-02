package arrays;

public class FindtheSingleTimeRepeatedElement {
	public static void main(String[] args) {
		int[] arr = { 3, 4, 5, 6, 3, 6, 5 };
		int result = 0;
		for (int numbers : arr) {
			result = result ^ numbers;
		}
		System.out.println(result);
	}

}
