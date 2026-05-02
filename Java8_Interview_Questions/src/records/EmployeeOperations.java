package records;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

record Employee(int id, String name, int age, String gender, String department, int yearOfJoining, double salary) {

}

public class EmployeeOperations {
	public static void main(String[] args) {

		// 1. How many male and female employees are there in an organization
		Map<String, Long> employeesBasedOnGender = getEmployees().stream()
				.collect(Collectors.groupingBy(Employee::gender, Collectors.counting()));
		System.out.println(employeesBasedOnGender);

		// 2. Print the name of all departments in an Organization
		getEmployees().stream().map(Employee::department).distinct().forEach(depts -> System.out.print(depts + " "));
		System.out.println();

		// 3. What is the avg age of male and female employees
		Map<String, Double> averageAge = getEmployees().stream()
				.collect(Collectors.groupingBy(Employee::gender, Collectors.averagingInt(Employee::age)));
		System.out.println(averageAge);

		// 4. Get the Details of highest paid employee in the organization
		Optional<Employee> highestPaidEmployee = getEmployees().stream()
				.max(Comparator.comparingDouble(Employee::salary));
		if (highestPaidEmployee.isPresent()) {
			System.out.println(highestPaidEmployee.get());
		} else {
			System.out.println("No employee present with highest salary");
		}

		// 5. Get the name of all employees who has joined after 2015
		List<String> namesOfEmployees = getEmployees().stream().filter(emp -> emp.yearOfJoining() > 2015)
				.map(Employee::name).toList();
		System.out.println(namesOfEmployees);

		// 6. Count the number of employees in each department
		Map<String, Long> countOfEmployes = getEmployees().stream()
				.collect(Collectors.groupingBy(Employee::department, Collectors.counting()));
		System.out.println(countOfEmployes);

		// 7. What is the average salary of each department
		Map<String, Double> averagesalary = getEmployees().stream()
				.collect(Collectors.groupingBy(Employee::department, Collectors.averagingDouble(Employee::salary)));
		System.out.println(averagesalary);

		// 8. Get the youngest male employee details in the Finance department
		Optional<Employee> yougestEmployeeInFinance = getEmployees().stream()
				.filter(dept -> dept.department().equals("Finance") && dept.gender().equals("Male"))
				.min(Comparator.comparing(Employee::age));
		if (yougestEmployeeInFinance.isPresent()) {
			System.out.println(yougestEmployeeInFinance.get());
		}

		// 9. who has the most working experience in the organization
		Optional<Employee> experienceEmployee = getEmployees().stream()
				.collect(Collectors.minBy(Comparator.comparing(Employee::yearOfJoining)));
		if (experienceEmployee.isPresent()) {
			System.out.println(experienceEmployee.get());
		}

		// 10. How many male and female employees are there in the Finance department
		Map<String, Long> maleAndFemale = getEmployees().stream().filter(emp -> emp.department().equals("Finance"))
				.collect(Collectors.groupingBy(Employee::gender, Collectors.counting()));
		System.out.println(maleAndFemale);
	}

	private static List<Employee> getEmployees() {
		List<Employee> employees = new ArrayList<>();

		employees.add(new Employee(1, "Jhansi", 32, "Female", "HR", 2011, 25000.0));
		employees.add(new Employee(2, "Raj", 28, "Male", "Finance", 2015, 35000.0));
		employees.add(new Employee(3, "Sneha", 26, "Female", "IT", 2018, 45000.0));
		employees.add(new Employee(4, "Amit", 35, "Male", "IT", 2010, 60000.0));
		employees.add(new Employee(5, "Neha", 30, "Female", "Finance", 2012, 30000.0));
		employees.add(new Employee(6, "Kiran", 27, "Male", "Sales", 2019, 28000.0));
		employees.add(new Employee(7, "Pooja", 29, "Female", "Marketing", 2016, 32000.0));
		employees.add(new Employee(8, "Ravi", 31, "Male", "Marketing", 2013, 40000.0));
		employees.add(new Employee(9, "Swati", 34, "Female", "Sales", 2010, 41000.0));
		employees.add(new Employee(10, "Varun", 24, "Male", "HR", 2020, 22000.0));
		employees.add(new Employee(11, "Divya", 33, "Female", "IT", 2009, 65000.0));
		employees.add(new Employee(12, "Suresh", 36, "Male", "Finance", 2008, 55000.0));

		return employees;
	}

}
