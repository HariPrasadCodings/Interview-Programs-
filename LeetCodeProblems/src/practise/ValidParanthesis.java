package practise;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ValidParanthesis {
	public static void main(String[] args) {
		String s = "[()]{}";
		System.out.println(isValid(s));
	}

	// private static boolean isValid(String s) {
	//
	// Stack<Character> stack = new Stack<>();
	//
	// for (char c : s.toCharArray()) {
	// if (c == '(' || c == '{' || c == '[') {
	// stack.push(c);
	//
	// } else {
	// if (stack.isEmpty()) {
	// return false;
	// }
	// char top = stack.pop();
	// if (c == ')' && top != '(' || c == '}' && top != '{'
	// || c == ']' && top != '[') {
	// return false;
	// }
	// }
	//
	// }
	// return stack.isEmpty();
	// }

	// approach: 2
	private static boolean isValid(String s) {
		Stack<Character> stack = new Stack<>();
		Map<Character, Character> map = new HashMap<>();
		map.put(')', '(');
		map.put('}', '{');
		map.put(']', '[');

		for (char c : s.toCharArray()) {
			if (map.containsKey(c)) {
				char top = stack.isEmpty() ? '#' : stack.pop();
				if (top != map.get(c)) {
					return false;
				}
			} else {
				stack.push(c);
			}
		}
		return stack.isEmpty();

	}

}
