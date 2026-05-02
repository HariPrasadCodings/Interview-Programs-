package date_04_07_2025;

public enum Singleton {
	INSTANCE;

	public int value;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public static void main(String[] args) {

		Singleton s1 = Singleton.INSTANCE;
		s1.setValue(20);

		Singleton s2 = Singleton.INSTANCE;

		System.out.println(s2.getValue());

	}

}
