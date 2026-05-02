package features;

public class CheckGivenYearIsLeapYearOrNot {
	public static void main(String[] args) {
		System.out.println(checkYear(2025));
		System.out.println(checkYear(2026));
		System.out.println(checkYear(2028));

	}

	private static String checkYear(Number year) {

		return switch (year) {
		case Integer y -> (y % 4 == 0 && y % 100 != 0) || (y % 400 == 0) ? "Leap year" : "Common year";
		default -> "Invalid year";
		};
	}

}
