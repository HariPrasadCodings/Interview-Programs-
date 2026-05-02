package java8;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EmployeeTestUsingJava8 {
	public static void main(String[] args) {

		// 1.Group the Employees by city.

		Map<String, Long> employeesByCity = employees().stream()
				.collect(Collectors.groupingBy(Employee::getCity, Collectors.counting()));
		System.out.println(employeesByCity);

		// 2. Group the Employees by age.

		Map<Integer, List<Employee>> employeesByAge = employees().stream()
				.collect(Collectors.groupingBy(Employee::getAge));
		System.out.println(employeesByAge);

		// 3. Find the count of male and female employees present in the organization.

		Map<String, Long> maleAndFemaleCount = employees().stream()
				.collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
		System.out.println(maleAndFemaleCount);

		// 4. Print the names of all departments in the organization.
		List<String> employeeDepartments = employees().stream().map(Employee::getDeptName).distinct().toList();
		System.out.println(employeeDepartments);

		// 5. Print employee details whose age is greater than 28.
		List<Employee> employeesGreaterThan28 = employees().stream().filter(emp -> emp.getAge() > 28).toList();
		System.out.println(employeesGreaterThan28);

		// 6. Find maximum age of employee.
		int employeeMaxAge = employees().stream().mapToInt(Employee::getAge).max().getAsInt();
		System.out.println(employeeMaxAge);

		// 7. Print Average age of Male and Female Employees.
		Map<String, Double> avgAgeOfMaleAndFemale = employees().stream()
				.collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingInt(Employee::getAge)));
		System.out.println(avgAgeOfMaleAndFemale);

		// 8. Print the number of employees in each department.
		Map<String, Long> noOfEmployeesInDept = employees().stream()
				.collect(Collectors.groupingBy(Employee::getDeptName, Collectors.counting()));
		System.out.println(noOfEmployeesInDept);

		// 9. Find oldest employee.
		Employee oldEmployee = employees().stream().max(Comparator.comparing(Employee::getAge)).get();
		System.out.println(oldEmployee);

		// 10. Find youngest female employee.
		Employee youngEmployee = employees().stream().min(Comparator.comparing(Employee::getAge))
				.filter(emp -> emp.getGender().equals("F")).get();
		System.out.println(youngEmployee);

		// 11. Find employees whose age is greater than 30 and less than 30.

		Map<Boolean, List<Employee>> employeeWithAgeFilter = employees().stream()
				.collect(Collectors.partitioningBy(emp -> emp.getAge() > 30));

		for (Map.Entry<Boolean, List<Employee>> entry : employeeWithAgeFilter.entrySet()) {
			if (Boolean.TRUE.equals(entry.getKey())) {
				System.out.println("Employees greater than 30 years ::" + entry.getValue());
			} else {
				System.out.println("Employees less than 30 years ::" + entry.getValue());
			}
		}

		// 12. Find the department name which has the highest number of employees.
		String highestNumberOfEmployeesInDept = employees().stream()
				.collect(Collectors.groupingBy(Employee::getDeptName, Collectors.counting())).entrySet().stream()
				.max(Map.Entry.comparingByValue()).get().getKey();
		System.out.println(highestNumberOfEmployeesInDept);

		// 13. Find if there any employees from HR Department.
		List<Employee> hrDept = employees().stream().filter(emp -> emp.getDeptName().equals("HR"))
				.collect(Collectors.toList());
		System.out.println(hrDept);

		// 14. Find the department names that these employees work for, where the number
		// of employees in the department is over 3.
		employees().stream().collect(Collectors.groupingBy(Employee::getDeptName, Collectors.counting())).entrySet()
				.stream().filter(emp -> emp.getValue() > 3).forEach(System.out::println);

		// 15.Find distinct department names that employees work for.
		List<String> distinctDeptNames = employees().stream().map(Employee::getDeptName).distinct()
				.collect(Collectors.toList());
		System.out.println(distinctDeptNames);

	}

	private static List<Employee> employees() {
		List<Employee> empList = new ArrayList<>();
		empList.add(new Employee(1, "abc", 28, 123, "F", "HR", "Blore", 2020));
		empList.add(new Employee(2, "xyz", 29, 120, "F", "HR", "Hyderabad", 2015));
		empList.add(new Employee(3, "efg", 30, 115, "M", "HR", "Chennai", 2014));
		empList.add(new Employee(4, "def", 32, 125, "F", "HR", "Chennai", 2013));
		empList.add(new Employee(5, "ijk", 22, 150, "F", "IT", "Noida", 2013));
		empList.add(new Employee(6, "mno", 27, 140, "M", "IT", "Gurugram", 2017));
		empList.add(new Employee(7, "uvw", 26, 130, "F", "IT", "Pune", 2016));
		empList.add(new Employee(8, "pqr", 23, 145, "M", "IT", "Trivandam", 2015));
		empList.add(new Employee(9, "stv", 25, 160, "M", "IT", "Blore", 2010));
		return empList;
	}

}
