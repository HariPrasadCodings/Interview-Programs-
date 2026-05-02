package record;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public record EmployeeTest() {
	record Employee(String name, List<String> skills) {
	}
	public static void main(String[] args) {
		final List<String> requiredSkills = Arrays.asList("JavaScript",
				"React");
		List<Employee> employees = Arrays.asList(
				new Employee("Mercury",
						List.of("JavaScript", "React", "Node.js")),
				new Employee("Mars",
						List.of("Java", "Spring", "React", "Node.js")),
				new Employee("Venus", List.of("JavaScript", "React")),
				new Employee("Uranus", List.of("Python", "Django")));

		List<String> result = employees.stream()
				.filter(employee -> new HashSet<>(employee.skills())
						.containsAll(requiredSkills))
				.map(Employee::name).toList();
		System.out.println(result);
	}

}
