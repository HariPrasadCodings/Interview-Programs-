package features;

/**
 * 
 * Restricts class inheritance by specifying which classes can extend a given
 * class.
 * 
 */
sealed class Vehicle permits Car, Bike {

}

final class Car extends Vehicle {
	void drive() {
		System.out.println("Car is driving");
	}
}

final class Bike extends Vehicle {
	void ride() {
		System.out.println("Bike is riding");
	}
}

public class SealedExample {
	public static void main(String[] args) {
		Vehicle v1 = new Car();
		Vehicle v2 = new Bike();
		if (v1 instanceof Car c) {
			c.drive();
		}
		if (v2 instanceof Bike b) {
			b.ride();
		}
	}

}
