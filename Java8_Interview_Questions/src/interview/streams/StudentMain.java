package interview.streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class StudentMain {
	public static void main(String[] args) {

		List<Student> studentsList = Arrays.asList(new Student("hari", 1, "English", 70),
				new Student("ravi", 2, "Telugu", 60), new Student("ashok", 4, "Science", 50),
				new Student("siva", 3, "Maths", 65), new Student("suresh", 5, "Social", 75));

		// 1. Given a list of students, write a Java 8 code to partition the students
		// who got above 60% from those who didn’t?
		Map<Boolean, List<Student>> above60 = studentsList.stream()
				.collect(Collectors.partitioningBy(student -> student.getPercentage() > 60));
		System.out.println(above60);

		/*
		 * {false=[Student [name=ravi, id=2, subject=English, percentage=60.0], Student
		 * [name=ashok, id=4, subject=English, percentage=50.0]], true=[Student
		 * [name=hari, id=1, subject=English, percentage=70.0], Student [name=siva,
		 * id=3, subject=English, percentage=65.0], Student [name=suresh, id=5,
		 * subject=English, percentage=75.0]]}
		 */

		// 2. Given a list of students, write a Java 8 code to get the names of top 3
		// performing students?
		List<Student> top3Students = studentsList.stream()
				.sorted(Comparator.comparing(Student::getPercentage).reversed()).limit(3).collect(Collectors.toList());
		System.out.println(top3Students);

		/*
		 * [Student [name=suresh, id=5, subject=English, percentage=75.0], Student
		 * [name=hari, id=1, subject=English, percentage=70.0], Student [name=siva,
		 * id=3, subject=English, percentage=65.0]]
		 */

		// 3. Given a list of students, how do you get the name and percentage of each
		// student?
		Map<String, Double> groupingBy = studentsList.stream()
				.collect(Collectors.toMap(Student::getName, Student::getPercentage));
		System.out.println(groupingBy);

		/* {hari=70.0, siva=65.0, ravi=60.0, suresh=75.0, ashok=50.0} */

		// 4. Given a list of students, how do you get the subjects offered in the
		// college?

		List<String> subjectList = studentsList.stream().map(Student::getSubject).collect(Collectors.toList());
		System.out.println(subjectList);

		// [English, Telugu, Science, Maths, Social]

		// 5. Given a list of students, write a Java 8 code to get highest, lowest and
		// average percentage of students?

		// a. get highest percentage
		Optional<Student> highest = studentsList.stream().max(Comparator.comparing(Student::getPercentage));
		if (highest.isPresent()) {
			System.out.println("Highest Percentage: " + highest.get());
		}

		// b. get lowest percentage
		Optional<Student> lowest = studentsList.stream().min(Comparator.comparing(Student::getPercentage));
		if (lowest.isPresent()) {
			System.out.println("Highest Percentage: " + lowest.get());
		}

		// c. average percentage of students
		Double avgPercentage = studentsList.stream().collect(Collectors.averagingDouble(Student::getPercentage));
		System.out.println("Average percentage: " + avgPercentage);

		// a, b, c are in other way
		DoubleSummaryStatistics studentStats = studentsList.stream()
				.collect(Collectors.summarizingDouble(Student::getPercentage));
		System.out.println("Highest percentage: " + studentStats.getMax());
		System.out.println("Lowest percentage: " + studentStats.getMin());
		System.out.println("Average percentage: " + studentStats.getAverage());

		// 6.How do you get total number of students from the given list of students?

		// long count = studentsList.stream().count();
		Long count = studentsList.stream().collect(Collectors.counting());
		System.out.println(count);

		// 7. How do you get the students grouped by subject from the given list of
		// students?
		Map<String, List<Student>> groupedBySubject = studentsList.stream()
				.collect(Collectors.groupingBy(Student::getSubject));
		System.out.println(groupedBySubject);

	}

}
