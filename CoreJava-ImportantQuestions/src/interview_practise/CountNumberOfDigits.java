package interview_practise;

public class CountNumberOfDigits {
	public static void main(String[] args) {
		int n = 123456;

//		int count = 0;
//
//		while (n > 0) {
//			count++;
//			n = n / 10;
//		}
//		System.out.println(count);

		long totalDigits = String.valueOf(n).chars().count();
		System.out.println(totalDigits);
	}

}
