package interview.streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class EmployeeImpl {
	public static void main(String[] args) {
		List<Employee> employees = Arrays.asList(new Employee(1, "hari", 27, "male", "Java", 2022, 35000),
				new Employee(2, "sita", 29, "female", "Python", 2021, 40000),
				new Employee(3, "ram", 31, "male", "DevOps", 2020, 45000),
				new Employee(4, "lakshmi", 26, "female", "JavaScript", 2023, 30000),
				new Employee(5, "ganesh", 28, "male", "Cloud", 2022, 38000),
				new Employee(6, "krishna", 30, "male", "Data Science", 2019, 50000));

		// 1. Given a list of employees, write a Java 8 code to count the number of
		// employees in each department?
		Map<String, Long> groupBasedOnDept = employees.stream()
				.collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
		System.out.println(groupBasedOnDept);

		// {Data Science=1, Java=1, DevOps=1, Cloud=1, JavaScript=1, Python=1}

		// 2. Given a list of employees, find out the average salary of male and female
		// employees?
		Map<String, Double> avgSalaryOfMaleAndFemale = employees.stream()
				.collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingDouble(Employee::getSalary)));
		System.out.println(avgSalaryOfMaleAndFemale);

		// {female=35000.0, male=42000.0}

		// 3. Write a Java 8 code to get the details of highest paid employee in the
		// organization from the given list of employees?
		// approach: 1
		// Employee highestSalariedEmployee =
		// employees.stream().max(Comparator.comparing(Employee::getSalary)).get();
		// approach: 2
//		Employee highestSalariedEmployee = employees.stream()
//				.sorted(Comparator.comparing(Employee::getSalary).reversed()).findFirst().get();
		Employee highestSalariedEmployee = employees.stream()
				.collect(Collectors.maxBy(Comparator.comparing(Employee::getSalary))).get();
		System.out.println(highestSalariedEmployee);

		// 4. Write the Java 8 code to get the average age of each department in an
		// organization?
		Map<String, Double> avgAgeOfEmployees = employees.stream()
				.collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getAge)));
		System.out.println(avgAgeOfEmployees);

		// 5. Given a list of employees, how do you find out who is the senior most
		// employee in the organization?
		Employee seniorEmployee = employees.stream().sorted(Comparator.comparing(Employee::getYearOfJoining))
				.findFirst().get();
		System.out.println(seniorEmployee);

		// 6. Given a list of employees, get the details of the most youngest employee
		// in the organization?
		Employee youngestEmployee = employees.stream()
				.sorted(Comparator.comparing(Employee::getYearOfJoining).reversed()).findFirst().get();
		System.out.println(youngestEmployee);

		// approach: 2
		Employee youngest = employees.stream().min(Comparator.comparingInt(Employee::getAge)).get();
		System.out.println(youngest);

		// 7. How do you get the number of employees in each department if you have
		// given a list of employees?
		Map<String, Long> eachDeptEmployees = employees.stream()
				.collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
		System.out.println(eachDeptEmployees);

		// 8. Given a list of employees, find out the number of male and female
		// employees in the organization?
		Map<String, Long> numberOfMaleAndFemale = employees.stream()
				.collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
		System.out.println(numberOfMaleAndFemale);

		System.out.println(IntStream.range(0, 5).sum());

	}

}
