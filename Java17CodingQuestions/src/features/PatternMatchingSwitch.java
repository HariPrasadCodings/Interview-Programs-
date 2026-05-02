package features;

/**
 * Eliminates the need for multiple instanceof checks.
 * 
 */

public class PatternMatchingSwitch {

	static void process(Object obj) {
		switch (obj) {
		case Integer i -> System.out.println("Integer: " + i);
		case String s -> System.out.println("String: " + s);
		case null -> System.out.println("Null value: ");
		default -> System.out.println("Unknown Type " + obj);
		}
	}

	public static void main(String[] args) {
		process(10);
		process("Hari Prasad");
		process(null);
		process(10.25);
	}
}
