package practise;

public class IndexOfFirstSubString {
	public static void main(String[] args) {
		String haystric = "leetcode";
		String needle = "leet";
		
		System.out.println(findIndex(haystric, needle));
	}

	static int findIndex(String haystrick, String needle) {
		int n = haystrick.length();
		int m = needle.length();

		if (m > n)
			return -1;

		for (int i = 0; i < n - m; i++) {
			if (haystrick.substring(i, i + m).equals(needle)) {
				return i;
			}
		}
		return -1;
	}

}
