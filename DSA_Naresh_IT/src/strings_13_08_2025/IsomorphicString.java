package strings_13_08_2025;

public class IsomorphicString {
	public static void main(String[] args) {
		String s = "egg";
		String t = "add";

		System.out.println(checkStringIsomorphic(s, t));

	}

	static boolean checkStringIsomorphic(String s, String t) {
		if (s.length() != t.length()) {
			return false;
		}
		int[] sMap = new int[256];
		int[] tMap = new int[256];

		for (int i = 0; i < s.length(); i++) {
			if (sMap[s.charAt(i)] != tMap[t.charAt(i)]) {
				return false;
			}

			sMap[s.charAt(i)] = i + 1;
			tMap[t.charAt(i)] = i + 1;
		}

		return true;
	}

}
