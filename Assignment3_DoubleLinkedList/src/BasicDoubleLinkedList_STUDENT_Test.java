import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BasicDoubleLinkedList_STUDENT_Test {

	BasicDoubleLinkedList<Student> students;
	Student billy, sammy, emily, yao;
	
	@BeforeEach
	void setUp() throws Exception {
		students = new BasicDoubleLinkedList<Student>();
		billy = new Student("Billy", 21, 12345);
		sammy = new Student("Sammy", 32, 12346);
		emily = new Student("Emily", 28, 12344);
		yao = new Student("Yao", 19, 12355);
		
		students.addToEnd(billy);
		students.addToFront(sammy);
		students.addToEnd(emily);
		students.addToEnd(yao);
	}

	@AfterEach
	void tearDown() throws Exception {
		billy = sammy = emily = yao = null;
		students = null;
	}

	@Test
	void testAddToFrontAndGetFirst() {
		int size = students.getSize();
		Student jordan = new Student("Jordan", 18, 12356);
		students.addToFront(jordan);
		assertTrue(students.getFirst().equals(jordan));
		assertEquals(size+1, students.getSize());
	}
	
	@Test
	void testAddToEndAndGetLast() {
		int size = students.getSize();
		Student jordan = new Student("Jordan", 18, 12356);
		students.addToEnd(jordan);
		assertTrue(students.getLast().equals(jordan));
		assertEquals(size+1, students.getSize());
	}
	
	@Test
	void testRetrieveMethods() {
		int size = students.getSize();
		Student a = students.retrieveFirstElement();
		assertEquals(size-1, students.getSize());
		assertTrue(a.equals(sammy));
		Student b = students.retrieveLastElement();
		assertEquals(size-2, students.getSize());
		assertTrue(b.equals(yao));
	}
	
	@Test
	void testRemove() {
		int size = students.getSize();
		StudentIDComparator id_comparator = new StudentIDComparator();
		
		Student jordan = new Student("Jordan", 18, 12356);
		assertNull(students.remove(jordan, id_comparator));
		assertEquals(size, students.getSize());
		
		assertTrue(students.remove(billy, id_comparator).data.equals(billy));
		assertEquals(size-1, students.getSize());
	}
	
	@Test
	void testToArrayList() {
		ArrayList<Student> array = students.toArrayList();
		assertTrue(array.get(0).equals(sammy));
		assertTrue(array.get(1).equals(billy));
		assertTrue(array.get(2).equals(emily));
		assertTrue(array.get(3).equals(yao));
	}
	
	@Test
	void testIterator() {
		String comparison = "";
		for (Student s : students)
			comparison += s.getName();
		assertEquals("SammyBillyEmilyYao", comparison);
	}
	
	private class StudentIDComparator implements Comparator<Student> {
		@Override
		public int compare(Student a, Student b) {
			return a.student_id - b.student_id;
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
