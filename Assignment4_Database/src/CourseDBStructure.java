import java.io.IOException;
import java.util.ArrayList;

public class CourseDBStructure implements CourseDBStructureInterface{

	private CourseNode[] table;
	
	public CourseDBStructure(int n) {
		table = new CourseNode[getNearestPrimeGreaterThan(n)];
	}
	
	public CourseDBStructure(String string, int n) {
		table = new CourseNode[n];
	}

	public void resize(int n) {
		CourseDBStructure temp = new CourseDBStructure(n);
		for (int i = 0; i < table.length; i++) {
			CourseNode current = table[i];
			while(current != null) {
				temp.add(current.course);
				current = current.prev;
			}
		}
		this.table = temp.table;
		temp = null;
	}
	
	private int getNearestPrimeGreaterThan(int n) {
		int load_factor = n*2/3; // Same as n divided by 1.5 without converting to double
		int K = 1;
		int prime = 7; // first 4K+3 prime
		while (prime < load_factor || !isPrime(prime)) {
			prime = 4 * ++K + 3;
		}
		return prime;
	}
	
	private boolean isPrime(int num) {
		if (num <= 1)
			return false;
		int i = 2;
		while (i*i <= num) {
			if (num % i == 0) // if the number is evenly divisible by i then it's not prime
				return false;
			i++;
		}
		return true;
	}
	
	private int getHashCode(int num) {
		return Integer.toString(num).hashCode() % table.length;
	}
	
	@Override
	public void add(CourseDBElement element) {
		int hash = getHashCode(element.getCRN());
		
		if (table[hash] == null) {
			table[hash] = new CourseNode(element);
			return;
		}
		
		try {
			CourseDBElement existing = get(element.getCRN());
			CourseNode current = table[hash];
			while(current != null) {
				if (current.course.equals(existing)) {
					current.course = element;
					break;
				}
				current = current.prev;
			}
		}catch(IOException e) {
			table[hash] = table[hash].addAndReturnNext(element);
		}
	}

	@Override
	public CourseDBElement get(int crn) throws IOException {
		CourseNode current = table[getHashCode(crn)];
		while(current != null) {
			if (current.course.getCRN() == crn)
				return current.course;
			current = current.prev;
		}
		throw new IOException();
	}

	@Override
	public ArrayList<String> showAll() {
		ArrayList<String> courses = new ArrayList<String>();
		for (int i = 0; i < table.length; i++) {
			CourseNode current = table[i];
			while(current != null) { // For some reason the JUnit tests want the reverse order of a linked list
				courses.add(current.course.toString());
				current = current.prev;
			}
		}
		return courses;
	}

	@Override
	public int getTableSize() {
		return table.length;
	}
	
	private class CourseNode {
		private CourseNode prev;
		private CourseDBElement course;
		
		public CourseNode(CourseDBElement course) {
			this.course = course;
		}
		
		public CourseNode addAndReturnNext(CourseDBElement course) {
			CourseNode temp = new CourseNode(course);
			temp.prev = this;
			return temp;
		}
		
	}

}
