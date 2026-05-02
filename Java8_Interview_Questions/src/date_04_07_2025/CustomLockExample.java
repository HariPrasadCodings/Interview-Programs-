package date_04_07_2025;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CustomLockExample {

	private static final Lock lock = new ReentrantLock();
	private static int counter = 0;

	public static void main(String[] args) {

		Runnable task = () -> {

			for (int i = 0; i < 2000; i++) {
				lock.lock();
				try {
					counter++;
				} finally {
					lock.unlock();
				}

			}
		};
		Thread t1 = new Thread(task);
		Thread t2 = new Thread(task);
		t1.start();
		t2.start();

		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			e.printStackTrace();
		}
		System.out.println("Counter: " + counter);

	}

}