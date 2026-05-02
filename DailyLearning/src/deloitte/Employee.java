package deloitte;

public class Employee {

	private int id;
	private int salary;

	public Employee(int id, int salary) {
		this.id = id;
		this.salary = salary;
	}

	public int getId() {
		return id;
	}

	public int getSalary() {
		return salary;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", salary=" + salary + "]";
	}

}
