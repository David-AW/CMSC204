import java.io.IOException;
import java.util.ArrayList;

public class CourseDBStructure implements CourseDBStructureInterface{

	private CourseNode[] table; // Essentially an array of tail nodes from what is theoretically linked lists
	private int initial_prime, storage_count;
	
	public CourseDBStructure(int n) {
		initial_prime = getNearestPrimeGreaterThan(n);
		table = new CourseNode[initial_prime];
	}
	
	public CourseDBStructure(String string, int n) {
		table = new CourseNode[n];
		initial_prime = getNearestPrimeGreaterThan(n);
	}

	/**
	 * Resizes the table to the nearest prime of the load factor of parameter n/1.5
	 * @param n estimated number of courses
	 */
	private void resize(int n) {
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
	
	/**
	 * Finds the nearest 4K+3 prime larger than n
	 * @param n number to find the closest prime to
	 * @return the nearest 4K+3 prime larger than n
	 */
	private int getNearestPrimeGreaterThan(int n) {
		int load_factor = n*2/3; // Same as n divided by 1.5 without converting to double
		int K = 1;
		int prime = 7; // first 4K+3 prime
		while (prime < load_factor || !isPrime(prime)) {
			prime = 4 * ++K + 3;
		}
		return prime;
	}
	
	/**
	 * Exhaust the list of integers from 2 to (int)sqrt(num)+1, if nothing is divisible in that range then the number is prime
	 * @param num number to check for prime
	 * @return true if prime, false if not
	 */
	private boolean isPrime(int num) {
		if (num <= 1)
			return false;
		int i = 2;
		while (i*i <= num) {
			if (num % i == 0) // Found something divisible, so not prime.
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
		int current_load = (storage_count + 1) * 2 / 3;
		if (current_load >= initial_prime)
			resize(storage_count*2); // Double the size of the hash table
		
		int hash = getHashCode(element.getCRN());
		
		if (table[hash] == null) {
			table[hash] = new CourseNode(element);
			storage_count++;
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
			table[hash] = table[hash].addAndReturn(element); // Create a node for this element and add it as the tail
		}
		
		storage_count++;
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
		
		/**
		 * Adds a new node containing the course element to the tail of this linked list, then returns the new tail to be assigned to the hash table.
		 * @param course course to be added
		 * @return new tail node of the linked list.
		 */
		public CourseNode addAndReturn(CourseDBElement course) {
			CourseNode temp = new CourseNode(course);
			temp.prev = this;
			return temp;
		}
		
	}

}
