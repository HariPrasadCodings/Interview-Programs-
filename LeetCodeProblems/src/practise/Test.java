package practise;

public class Test {
	public static void main(String[] args) {
		int[] arr = {10, 20, 30, 40, 50};
		int index = 2;
		arr[index] = arr[arr.length - index];
		System.out.println(arr[2]);
	}

}
