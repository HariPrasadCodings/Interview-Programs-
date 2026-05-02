package deloitte;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

class EmployeeTwo {
	private String name;
	private int salary;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public EmployeeTwo(String name, int salary) {
		super();
		this.name = name;
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", salary=" + salary + "]";
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return Objects.hash(name, salary);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof EmployeeTwo))
			return false;
		EmployeeTwo employee = (EmployeeTwo) obj;
		return salary == employee.salary && Objects.equals(name, employee.name);
	}

}

class EmployeeComparator implements Comparator<EmployeeTwo> {

	@Override
	public int compare(EmployeeTwo o1, EmployeeTwo o2) {
		return Integer.compare(o1.getSalary(), o2.getSalary());
	}

}

public class ComparatorExample {
	public static void main(String[] args) {
		// Creating a list of Employee objects
		List<EmployeeTwo> employees = new ArrayList<>();
        employees.add(new EmployeeTwo("Alice", 70000));
        employees.add(new EmployeeTwo("Bob", 50000));
        employees.add(new EmployeeTwo("Charlie", 60000));

		Collections.sort(employees, new EmployeeComparator());

		for (EmployeeTwo emp : employees) {
			System.out.println(emp);
		}
	}

}
