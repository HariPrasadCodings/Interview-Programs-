package date_04_08_2025;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Stack;

public class DailyTemperatures {
	public static void main(String[] args) {
		int[] arr = { 73, 74, 75, 71, 69, 72, 76, 73 };

		int[] result = dailyTemperature(arr);

		System.out.println(Arrays.toString(result));
	}

	static int[] dailyTemperature(int[] arr) {
		Deque<Integer> stack = new ArrayDeque<>();

		int n = arr.length;

		int[] result = new int[n];

		for (int i = n - 1; i >= 0; i--) {
			// Remove all indices with a lower or equal temperature
			while (!stack.isEmpty() && arr[stack.peek()] <= arr[i]) {
				stack.pop();
			}

			// if the stack has still elements use the index difference
			if (!stack.isEmpty()) {
				result[i] = stack.peek() - i;
			}

			// push current index to stack
			stack.push(i);
		}
		return result;
	}

}
