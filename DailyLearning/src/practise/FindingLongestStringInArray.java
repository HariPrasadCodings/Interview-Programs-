package practise;

import java.util.Arrays;

public class FindingLongestStringInArray {
	public static void main(String[] args) {
		String[] names = { "HariPrasad", "Rajeshwari", "SaiAkruthi", "Shreya", "Gautham", "SaiLokesh", "Dileep",
				"Vinay" };

		int maxLength = Arrays.stream(names).mapToInt(String::length).max().orElse(0);

		Arrays.stream(names).filter(name -> name.length() == maxLength).forEach(System.out::println);
	}
}
