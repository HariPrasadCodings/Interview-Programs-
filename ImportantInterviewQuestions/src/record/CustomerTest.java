package record;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CustomerTest {

	record Customer(String customerId, double amount) {

	}

	public static void main(String[] args) {
		List<Customer> orders = List.of(new Customer("cust1", 240.0),
				new Customer("cust2", 340.0), new Customer("cust3", 140.0),
				new Customer("cust4", 40.0), new Customer("cust5", 20.0),
				new Customer("cust6", 80.0));
		Map<String, List<Customer>> highOrderByCustomer = orders.stream()
				.collect(Collectors.groupingBy(Customer::customerId,
						Collectors.filtering(o -> o.amount() >= 100,
								Collectors.toList())));
		System.out.println(highOrderByCustomer);

	}

}
