package practise;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupEmployeesByDepartment {

	record Employee(String name, String department) {
	};

	public static void main(String[] args) {

		List<Employee> employees = List.of(new Employee("Hari", "Java"), new Employee("Sneha", "HR"),
				new Employee("Kiran", "IT"), new Employee("Divya", "Finance"), new Employee("Akruthi", "Java"),
				new Employee("Rahul", "IT"), new Employee("Pooja", "HR"), new Employee("Sidharth", "Finance"));

		Map<String, List<String>> employeesByDept = employees.stream().collect(
				Collectors.groupingBy(Employee::department, Collectors.mapping(Employee::name, Collectors.toList())));

		employeesByDept.forEach((dept, names) -> System.out.println(dept + " -> " + names));
	}

}
