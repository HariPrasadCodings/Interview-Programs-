package queue;

import java.util.PriorityQueue;
import java.util.Queue;

public class DemoQueue {
	public static void main(String[] args) {
		Queue<Integer> queue = new PriorityQueue<>();
		queue.add(12);
		queue.add(13);
		queue.add(14);
		queue.add(15);
		queue.remove();
		System.out.println(queue.peek());
		System.out.println(queue.isEmpty());
		System.out.println(queue.size());
		System.out.println(queue);
		System.out.println(queue.poll());
		System.out.println(queue);

	}

}
