package strings_14_08_2025;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class RemoveOuterParanthesis {
	public static void main(String[] args) {
		String s = "(()())(())";

		System.out.println(removeOuterParanthesisUsingStack(s));
		System.out.println(removeOuterParanthesisUsingCounter(s));
	}

	// Approach 1: Counter Approach
	// TC: O(N) SC : O(N)
	static String removeOuterParanthesisUsingCounter(String s) {
		StringBuilder sb = new StringBuilder();
		int count = 0;

		for (char ch : s.toCharArray()) {
			if (ch == '(') {
				if (count > 0) {
					sb.append(ch);
				}
				count++;
			} else {
				count--;
				if (count > 0) {
					sb.append(ch);
				}
			}
		}
		return sb.toString();
	}

	// Approach 2: Stack
	static String removeOuterParanthesisUsingStack(String s) {
		Deque<Character> stack = new ArrayDeque<>();
		StringBuilder result = new StringBuilder();

		for (char c : s.toCharArray()) {
			if (c == '(') {
				if (!stack.isEmpty()) {
					result.append(c);
				}
				stack.push(c);
			} else {
				stack.pop();
				if (!stack.isEmpty()) {
					result.append(c);
				}
			}
		}
		return result.toString();
	}
}
