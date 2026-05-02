package map;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class IterateHashMap {
	public static void main(String[] args) {
		Map<String, String> names = new LinkedHashMap<>();
		names.put("Hari", "Java");
		names.put("Akruthi", "Data");
		names.put("Divya", "Testing");

		iterateHashMap(names);
	}

	/**
	 * Explanation: This method shows two ways to iterate over a HashMap: using an
	 * enhanced for- loop to traverse the entry set, and using an iterator in a
	 * while-loop to perform the same task.
	 */
	private static void iterateHashMap(Map<String, String> names) {
		// using advanced for loop
		for (Entry<String, String> name : names.entrySet()) {
			System.out.println(name.getKey() + " " + name.getValue());
		}
		System.out.println("======================");
		// using iterator
		Iterator<Map.Entry<String, String>> iterator = names.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, String> entry = iterator.next();
			System.out.println(entry.getKey() + " " + entry.getValue());
		}
	}

}
