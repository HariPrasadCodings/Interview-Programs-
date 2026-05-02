package practise;

public class Employees {
	private int id;
	private int salary;

	public int getId() {
		return id;
	}

	public int getSalary() {
		return salary;
	}

	public Employees(int id, int salary) {
		super();
		this.id = id;
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employees [id=" + id + ", salary=" + salary + "]";
	}
}
