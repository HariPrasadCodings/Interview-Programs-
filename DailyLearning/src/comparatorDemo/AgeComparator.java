package comparatorDemo;

import java.util.Comparator;

public class AgeComparator implements Comparator<Employee> {

	@Override
	public int compare(Employee o1, Employee o2) {
		return o1.getAge() - o2.getAge();
		// ex: 6 - 8 = -2 //negative
		// 8 - 4 = 4 // positive
		// 4 - 4 = 0 // equal
	}

}
