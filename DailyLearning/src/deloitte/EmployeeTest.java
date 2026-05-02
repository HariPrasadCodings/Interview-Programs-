package deloitte;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeTest {

	public static void main(String[] args) {
		List<Employee> employeeList = new ArrayList<>();
		employeeList.add(new Employee(1, 500));
		employeeList.add(new Employee(2, 1000));
		employeeList.add(new Employee(3, 1500));
		employeeList.add(new Employee(4, 2000));
		employeeList.add(new Employee(5, 2500));
		employeeList.add(new Employee(6, 3000));
		employeeList.add(new Employee(7, 3500));

		// Print the employee salaries in descending order
		List<Employee> descendingOrder = employeeList.stream()
				.sorted(Comparator.comparing(Employee::getSalary).reversed())
				.collect(Collectors.toList());
		System.out.println(descendingOrder);

		// approach: 2
		List<Employee> sorted = employeeList.stream()
				.sorted((o1, o2) -> o2.getSalary() - o1.getSalary())
				.collect(Collectors.toList());
		System.out.println(sorted);

		// fetch top 3 employee salaries
		employeeList.stream().sorted(Comparator.comparing(Employee::getSalary))
				.skip(4).collect(Collectors.toList())
				.forEach(System.out::println);

		// fetch all employees having salary less than 3rd highest salary
		List<Employee> salariesLessThan3rdHighest = employeeList.stream()
				.sorted(Comparator.comparing(Employee::getSalary).reversed())
				.skip(3).collect(Collectors.toList());
		System.out.println(salariesLessThan3rdHighest);

		int[] arr = {2, 3, 4, 5, 7};
		int sum = Arrays.stream(arr).sum();
		System.out.println("Sum: " + sum);
	}

}
