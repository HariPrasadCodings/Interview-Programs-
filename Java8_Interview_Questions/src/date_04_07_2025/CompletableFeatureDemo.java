package date_04_07_2025;

import java.util.concurrent.CompletableFuture;

public class CompletableFeatureDemo {
	public static void main(String[] args) {
		CompletableFuture.supplyAsync(() -> {

			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				e.printStackTrace();
			}

			return "Hello";

		}).thenApply(s -> s + " world").thenAccept(System.out::println);
	}
}
