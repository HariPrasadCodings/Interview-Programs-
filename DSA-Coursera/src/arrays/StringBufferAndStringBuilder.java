package arrays;

public class StringBufferAndStringBuilder {
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		System.out.println("***********String Buffer*************");
		StringBuffer buffer = new StringBuffer("Hello ");
		System.out.println(buffer.capacity());
		System.out.println(buffer);
		for (int i = 0; i <= 1000; i++) {
			buffer.append("World");
		}
		System.out.println(System.currentTimeMillis() - startTime);
		System.out.println();

		System.out.println("************String Builder************");
		StringBuilder builder = new StringBuilder("Hello ");
		System.out.println(builder.capacity());
		for (int i = 0; i <= 1000; i++) {
			builder.append("World");
		}
		System.out.println(System.currentTimeMillis() - startTime);
	}

}
