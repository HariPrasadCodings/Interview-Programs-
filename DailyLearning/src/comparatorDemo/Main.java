package comparatorDemo;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
	public static void main(String[] args) {

		List<Employee> employees = Arrays.asList(new Employee(1, "hari", 27, new Address("ksp", 516)),
				new Employee(2, "ravi", 23, new Address("kdp", 216)),
				new Employee(3, "siva", 24, new Address("cht", 116)),
				new Employee(4, "chinna", 26, new Address("nlr", 316)),
				new Employee(5, "pandu", 29, new Address("kkd", 456)));

		System.out.println("Unsorted list: " + employees);

		// sort based on age
		// ascending order natural order
		Collections.sort(employees, new AgeComparator());
		System.out.println(employees + " "); // 2,3,4,1,5

		Comparator<Employee> comparator = (Employee e1, Employee e2) -> e1.getAge() - e2.getAge();
		employees.sort(comparator);
		System.out.println(employees);

		// sort based on name
		Collections.sort(employees, new NameComparator());
		System.out.println(employees + " "); // 4,1,5,2,3

		// sort based on the address
		Collections.sort(employees, new AddressComparator());
		System.out.println(employees + " "); // 3,2,4,5,1
	}

}
