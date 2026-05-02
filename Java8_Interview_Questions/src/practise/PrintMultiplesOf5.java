package practise;

import java.util.List;

public class PrintMultiplesOf5 {
	public static void main(String[] args) {
		List<Integer> numbers = List.of(1, 2, 3, 4, 15, 25, 67, 85, 29, 30);
		List<Integer> multiplesOf5 = numbers.stream().filter(n -> n % 5 == 0).toList();
		System.out.println(multiplesOf5);
	}

}
