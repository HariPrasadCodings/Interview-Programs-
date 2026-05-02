package test;

public class ArrayTest3 {
	public static void main(String[] args) {
		int[][] arr = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

		int sum = 0;
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j) {
				sum = sum + arr[i][j];
			}
		}
		System.out.println(sum / 5);

		String str = "Java";
		str.concat(" Programming");
		System.out.println(str);

		String s1 = "abc";
		StringBuffer s2 = new StringBuffer(s1);
		System.out.println("s2: " + s2);
		System.out.println(s1.equals(s2));

		String chars[] = {"x", "y", "z", "x", "y"};
		for (int i = 0; i < chars.length; ++i) {
			for (int j = i + 1; j < chars.length; ++j) {
				if (chars[i].compareTo(chars[j]) == 0) {
					System.out.print(chars[j]);
				}
			}
		}
	}

}
