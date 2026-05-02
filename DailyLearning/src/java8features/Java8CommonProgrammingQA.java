package java8features;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Java8CommonProgrammingQA {
	public static void main(String[] args) {
		List<Student> studentList = Stream.of(
				new Student(1, "Rohit", 30, "Male", "Mechanical Engineering", "Mumbai", 122,
						Arrays.asList("+912632632782", "+1673434729929")),
				new Student(2, "Pulkit", 56, "Male", "Computer Engineering", "Delhi", 67,
						Arrays.asList("+912632632762", "+1673434723929")),
				new Student(3, "Ankit", 25, "Female", "Mechanical Engineering", "Kerala", 164,
						Arrays.asList("+912632633882", "+1673434709929")),
				new Student(4, "Satish Ray", 30, "Male", "Mechanical Engineering", "Kerala", 26,
						Arrays.asList("+9126325832782", "+1671434729929")),
				new Student(5, "Roshan", 23, "Male", "Biotech Engineering", "Mumbai", 12,
						Arrays.asList("+012632632782")),
				new Student(6, "Chetan", 24, "Male", "Mechanical Engineering", "Karnataka", 90,
						Arrays.asList("+9126254632782", "+16736784729929")),
				new Student(7, "Arun", 26, "Male", "Electronics Engineering", "Karnataka", 324,
						Arrays.asList("+912632632782", "+1671234729929")),
				new Student(8, "Nam", 31, "Male", "Computer Engineering", "Karnataka", 433,
						Arrays.asList("+9126326355782", "+1673434729929")),
				new Student(9, "Sonu", 27, "Female", "Computer Engineering", "Karnataka", 7,
						Arrays.asList("+9126398932782", "+16563434729929", "+5673434729929")),
				new Student(10, "Shubham", 26, "Male", "Instrumentation Engineering", "Mumbai", 98,
						Arrays.asList("+912632646482", "+16734323229929")))
				.collect(Collectors.toList());

		// 1. Find the students whose rank is in between 50 to 100
		List<Student> studentsRanks = studentList.stream().filter(stu -> stu.getRank() > 50 && stu.getRank() < 100)
				.collect(Collectors.toList());
		System.out.println(studentsRanks);

		// 2. Find the Students who stays is Karnataka and sort them by their names in
		// ascending order
		List<Student> studentsBelongsToKarnataka = studentList.stream()
				.filter(student -> student.getCity().equals("Karnataka"))
				.sorted(Comparator.comparing(Student::getFirstName, Comparator.naturalOrder()))
				.collect(Collectors.toList());
		System.out.println(studentsBelongsToKarnataka);

		// 3. Find all department names
		Set<String> deptNames = studentList.stream().map(stu -> stu.getDept()).collect(Collectors.toSet());
		System.out.println(deptNames);

		// 4. Fetch all the contact numbers from the student list // use map when 1-to-1
		// mapping but here contacts is another list of object inside the list
		studentList.stream().map(stu -> stu.getContacts()).collect(Collectors.toList());
		// use flatMap when there is one-to-many mapping ex: here one student and he has
		// multiple contacts
		List<String> contactList = studentList.stream().flatMap(stu -> stu.getContacts().stream())
				.collect(Collectors.toList());
		System.out.println(contactList);

		// 5. Group the students by department names
		Map<String, Long> groupingByDept = studentList.stream()
				.collect(Collectors.groupingBy(Student::getDept, Collectors.counting()));
		System.out.println(groupingByDept);

		// 6. Find the maximum students belongs to which department
		String deptNameWhichHasMoreStudents = studentList.stream()
				.collect(Collectors.groupingBy(Student::getDept, Collectors.counting())).entrySet().stream()
				.filter(st -> st.getValue() > 3).map(Map.Entry::getKey).findFirst().get();
		// approach:2
		String deptName = groupingByDept.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();
		System.out.println(deptNameWhichHasMoreStudents + "  :  " + deptName);

		// 7. Find the average age of male and female students
		Map<String, Double> avgAgeOfStudents = studentList.stream()
				.collect(Collectors.groupingBy(Student::getGender, Collectors.averagingInt(Student::getAge)));
		System.out.println(avgAgeOfStudents);

		// 8. Find the highest rank in each department
		Map<String, Optional<Student>> highestRank = studentList.stream().collect(
				Collectors.groupingBy(Student::getDept, Collectors.minBy(Comparator.comparing(Student::getRank))));
		System.out.println(highestRank);

		// 9.Find the student who has the second rank
		Student secondRank = studentList.stream().sorted(Comparator.comparing(Student::getRank)).skip(1).findFirst()
				.get();
		System.out.println(secondRank);
	}

}
