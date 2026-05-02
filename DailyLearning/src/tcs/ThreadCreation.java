package tcs;

class MyThread extends Thread {
	@Override
	public void run() {
		System.out.println("Thread is running with Thread class " + Thread.currentThread().getName());
	}
}

class MyRunnable implements Runnable {
	@Override
	public void run() {
		System.out.println("Thread is running with Runnable interface " + Thread.currentThread().getName());
	}
}

public class ThreadCreation extends Thread {
	public static void main(String[] args) {
		MyThread thread = new MyThread();
		thread.start();

		MyRunnable runnable = new MyRunnable();
		Thread thread2 = new Thread(runnable);
		thread2.start();

		// Using lambda for Runnable
		Thread thread3 = new Thread(() -> {
			System.out.println("Thread is running: " + Thread.currentThread().getName());
		});
		thread3.start(); // Start the thread
	}

	@Override
	public void run() {
		super.run();
	}

}
