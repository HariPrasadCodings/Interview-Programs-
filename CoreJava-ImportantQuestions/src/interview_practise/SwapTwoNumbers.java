package interview_practise;

public class SwapTwoNumbers {
	public static void main(String[] args) {
		int a = 10;
		int b = 20;

		System.out.println("Before swapping: " + a + " and " + b);

//		a = a + b;
//		b = a - b;
//		a = a - b;
		
		// approach: 2
		int temp = a;
		a = b;
		b = temp;
		
		System.out.println("");

		System.out.println("After swapping: " + a + " and " + b);
	}

}
