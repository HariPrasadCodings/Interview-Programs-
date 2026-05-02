package tcs;

import java.util.HashMap;
import java.util.Map;

public class HashMapChangesInJava8 {
	public static void main(String[] args) {
		Map<Integer, String> map = new HashMap<>();

		for (int i = 0; i <= 12; i++) {
			map.put(i * 16, "Value " + 1);
		}
		System.out.println("Map content: " + map);
	}
}
