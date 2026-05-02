package leetCode_75_Programs.strings;

import java.util.Stack;

public class ValidParanthesis {
	public static void main(String[] args) {
		String s = "({[]})";
		System.out.println(isValidParenthesis(s));
	}

	static boolean isValidParenthesis(String s) {
		Stack<Character> stack = new Stack<>();

		for (char c : s.toCharArray()) {
			if (c == '(' || c == '{' || c == '[') {
				stack.push(c);
			} else {
				if (stack.isEmpty()) {
					return false;
				}
				char top = stack.pop();
				if (c == ')' && top != '(' || c == '}' && top != '{' || c == ']' && top != '[') {
					return false;
				}
			}
		}
		return stack.isEmpty();
	}

}
