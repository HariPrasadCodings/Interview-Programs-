package interview.streams;

import java.util.stream.Stream;

public class ArrayToStreamConvert {
	public static void main(String[] args) {

		String[] names = { "Java", "Spring", "SpringBoot", "Microservices" };
		Stream<String> stream = Stream.of(names);
		stream.forEach(System.out::println);

	}
}
