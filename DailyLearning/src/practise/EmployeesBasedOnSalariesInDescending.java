package practise;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class EmployeesBasedOnSalariesInDescending {
	public static void main(String[] args) {

		// Sort employees based on their salaries in descending order

		System.out.println("===Employees in descending order based on salary====");
		getEmployees().stream().sorted(Comparator.comparing(Employees::getSalary).reversed())
				.forEach(System.out::println);

		System.out.println("====Top 3 salaried employees====");
		// Fetch top 3 salaried employees
		getEmployees().stream().sorted(Comparator.comparing(Employees::getSalary).reversed()).limit(3)
				.forEach(System.out::println);

		// Fetch all employees having salary less than 3rd highest
		System.out.println("======Employees having salary less than 3rd highest=====");
		getEmployees().stream().sorted(Comparator.comparing(Employees::getSalary).reversed()).skip(3)
				.forEach(System.out::println);

	}

	private static List<Employees> getEmployees() {
		List<Employees> employees = new ArrayList<>();
		employees.add(new Employees(1, 500));
		employees.add(new Employees(2, 1000));
		employees.add(new Employees(3, 1500));
		employees.add(new Employees(4, 2000));
		employees.add(new Employees(5, 2500));
		employees.add(new Employees(6, 3000));
		employees.add(new Employees(7, 3500));
		return employees;
	}
}
