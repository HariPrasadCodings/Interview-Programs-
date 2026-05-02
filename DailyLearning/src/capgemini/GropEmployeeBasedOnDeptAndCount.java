package capgemini;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GropEmployeeBasedOnDeptAndCount {
	public static void main(String[] args) {
		List<Employee> employees = Arrays.asList(
				new Employee(1, "Hari", 45000, "IT"),
				new Employee(1, "Hari", 25000, "HR"),
				new Employee(1, "Hari", 55000, "Java"),
				new Employee(1, "Hari", 15000, "Devops"));

		Map<String, Long> groupedDepts = employees.stream().collect(Collectors
				.groupingBy(Employee::getDept, Collectors.counting()));
		System.out.println(groupedDepts);

		// Find3rdHighestSalaryOfEmployee

		double thirdrdHighest = employees.stream()
				.sorted(Comparator.comparing(Employee::getSalary).reversed())
				.map(Employee::getSalary).skip(2).findFirst().get();
		System.out.println(thirdrdHighest);
	}

}
