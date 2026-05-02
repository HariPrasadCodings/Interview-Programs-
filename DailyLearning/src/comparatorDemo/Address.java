package comparatorDemo;

public class Address implements Comparable<Address> {

	private String streetName;
	private int pin;

	public Address(String streetName, int pin) {
		super();
		this.streetName = streetName;
		this.pin = pin;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public int getPin() {
		return pin;
	}

	public void setId(int pin) {
		this.pin = pin;
	}

	@Override
	public String toString() {
		return "Address [streetName=" + streetName + ", pin=" + pin + "]";
	}

	@Override
	public int compareTo(Address o) {
		return this.getPin() - o.getPin();
	}

}
