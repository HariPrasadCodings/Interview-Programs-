package string;

public class SubString {
	public static void main(String[] args) {
		String originalString = "hariprasad";
		String substring = originalString.substring(2, 5).intern(); // r i p
		System.out.println(substring);
		
	}

}
