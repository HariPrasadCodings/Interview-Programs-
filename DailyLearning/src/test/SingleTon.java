package test;

public class SingleTon {

	public static SingleTon instance = null;

	private SingleTon() {

	}

	public static SingleTon getInstance() {
		synchronized (SingleTon.class) {
			if (instance == null) {
				instance = new SingleTon();
			}
		}
		return instance;

	}

	public static void main(String[] args) {
		SingleTon ton = SingleTon.getInstance();
		System.out.println(ton.hashCode());
		SingleTon ton1 = SingleTon.getInstance();
		System.out.println(ton1.hashCode());
	}

}
