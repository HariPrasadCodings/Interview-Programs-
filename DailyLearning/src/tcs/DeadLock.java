package tcs;

class Resource {
	String name;

	Resource(String name) {
		this.name = name;
	}
}

public class DeadLock {
	public static void main(String[] args) {
		Resource resource1 = new Resource("Resource1");
		Resource resource2 = new Resource("Resource2");

		Thread thread1 = new Thread(() -> {
			synchronized (resource1) {
				System.out.println("Thread-1 locked " + resource1.name);
				// Simulate some work
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				synchronized (resource2) {
					System.out.println("Thread-1 locked " + resource2.name);
				}
			}
		});

		Thread thread2 = new Thread(() -> {
			synchronized (resource2) {
				System.out.println("Thread-2 locked " + resource2.name);
				// Simulate some work
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				synchronized (resource1) {
					System.out.println("Thread-2 locked " + resource1.name);
				}
			}
		});

		thread1.start();
		thread2.start();

	}

}
