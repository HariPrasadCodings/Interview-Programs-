package deloitte;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Employees implements Comparable<Employees> {
	private String name;
	private double salary;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Employees(String name, double salary) {
		super();
		this.name = name;
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", salary=" + salary + "]";
	}

	@Override
	public int compareTo(Employees o) {
		return Double.compare(this.salary, o.salary);
	}

}

public class ComparableExample {
	public static void main(String[] args) {

		// Creating a list of Employee objects
		List<Employees> employees = new ArrayList<>();
		employees.add(new Employees("Alice", 50000));
		employees.add(new Employees("Bob", 60000));
		employees.add(new Employees("Charlie", 55000));

		// Sorting the employees by salary
		Collections.sort(employees);

		// Displaying the sorted list
		System.out.println("Employees sorted by salary:");

		for (Employees emp : employees) {
			System.out.println(emp);
		}

	}

}
