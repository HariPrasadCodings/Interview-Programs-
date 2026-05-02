package test;

public class SingleTonDemo {

	public static SingleTonDemo INSTANCE = null;

	private SingleTonDemo() {

	}

	public static SingleTonDemo getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new SingleTonDemo();
		}
		return INSTANCE;
	}

	public static void main(String[] args) {
		SingleTonDemo demo1 = SingleTonDemo.getInstance();
		System.out.println(demo1.hashCode());
		SingleTonDemo demo2 = SingleTonDemo.getInstance();
		System.out.println(demo2.hashCode());

	}

}
