import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SortedDoubleLinkedList_STUDENT_Test {

	StudentIDComparator id_comparator;
	StudentAgeComparator age_comparator;
	StudentNameComparator name_comparator;
	
	SortedDoubleLinkedList<Student> sorted_on_id;
	SortedDoubleLinkedList<Student> sorted_on_age;
	SortedDoubleLinkedList<Student> sorted_on_name;
	
	Student billy, sammy, emily, yao;
	
	@BeforeEach
	void setUp() throws Exception {
		id_comparator = new StudentIDComparator();
		age_comparator = new StudentAgeComparator();
		name_comparator = new StudentNameComparator();
		
		sorted_on_id = new SortedDoubleLinkedList<Student>(id_comparator);
		sorted_on_age = new SortedDoubleLinkedList<Student>(age_comparator);
		sorted_on_name = new SortedDoubleLinkedList<Student>(name_comparator);
		
		billy = new Student("Billy", 21, 12345);
		sammy = new Student("Sammy", 32, 12346);
		emily = new Student("Emily", 28, 12344);
		yao = new Student("Yao", 19, 12355);
		
		sorted_on_id.add(billy);
		sorted_on_id.add(sammy);
		sorted_on_id.add(emily);
		sorted_on_id.add(yao);
		
		sorted_on_age.add(billy);
		sorted_on_age.add(sammy);
		sorted_on_age.add(emily);
		sorted_on_age.add(yao);
		
		sorted_on_name.add(billy);
		sorted_on_name.add(sammy);
		sorted_on_name.add(emily);
		sorted_on_name.add(yao);
	}

	@AfterEach
	void tearDown() throws Exception {
		billy = sammy = emily = yao = null;
		id_comparator = null;
		age_comparator = null;
		name_comparator = null;
		sorted_on_id = null;
		sorted_on_age = null;
		sorted_on_name = null;
	}

	@Test
	void testAdd() {
		Student jordan = new Student("Jordan", 24, 12353);
		
		int size = sorted_on_id.getSize();
		sorted_on_id.add(jordan);
		assertEquals(size+1, sorted_on_id.getSize());
		assertTrue(sorted_on_id.toArrayList().get(3).equals(jordan));
		
		size = sorted_on_age.getSize();
		sorted_on_age.add(jordan);
		assertEquals(size+1, sorted_on_age.getSize());
		assertTrue(sorted_on_age.toArrayList().get(2).equals(jordan));
		
		size = sorted_on_name.getSize();
		sorted_on_name.add(jordan);
		assertEquals(size+1, sorted_on_name.getSize());
		assertTrue(sorted_on_name.toArrayList().get(2).equals(jordan));
	}
	
	@Test
	void testRemove() {
		int size = sorted_on_id.getSize();
		
		Student jordan = new Student("Jordan", 18, 12356);
		assertNull(sorted_on_id.remove(jordan, id_comparator));
		assertEquals(size, sorted_on_id.getSize());
		
		assertTrue(sorted_on_id.remove(billy, id_comparator).data.equals(billy));
		assertEquals(size-1, sorted_on_id.getSize());
	}
	
	private class StudentIDComparator implements Comparator<Student> {
		@Override
		public int compare(Student a, Student b) {
			return a.student_id - b.student_id;
		}
	}
	
	private class StudentAgeComparator implements Comparator<Student> {
		@Override
		public int compare(Student a, Student b) {
			return a.age - b.age;
		}
	}

	private class StudentNameComparator implements Comparator<Student> {
		@Override
		public int compare(Student a, Student b) {
			return a.name.compareTo(b.name);
		}
	}
	
	private final class Student {
		
		private final String name;
		private final int age, student_id;
		
		public Student(String name, int age, int student_id) {
			this.name = name;
			this.age = age;
			this.student_id = student_id;
		}
		
		public int getAge() {
			return age;
		}
		
		public String getName() {
			return name;
		}
		
		public int getStudentID() {
			return student_id;
		}
		
		@Override
		public String toString() {
			return "[M" + getStudentID() + "] " + getName() + " | " + getAge();
		}
		
		@Override
		public boolean equals(Object obj) {
			if (obj instanceof Student) {
				Student other = (Student) obj;
				return this.name.equals(other.name) && this.age == other.age && this.student_id == other.student_id;
			}
			return false;
		}
		
	}
}
