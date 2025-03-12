import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class BasicDoubleLinkedList<T> implements Iterable<T>{

	protected Node head;
	protected Node tail;
	protected int size = 0;

	public void addToFront(T data) {
		Node temp = new Node(data);
		if (head == null) {
			head = temp;
			tail = temp;
			size = 1;
			return;
		}
		temp.next = head;
		head.prev = temp;
		head = temp;
		size++;
	}
	
	public void addToEnd(T data) {
		Node temp = new Node(data);
		if (head == null) {
			addToFront(data);
			return;
		}
		tail.next = temp;
		temp.prev = tail;
		tail = temp;
		size++;
	}
	
	public T getFirst() {
		return head.data;
	}

	public T getLast() {
		return tail.data;
	}

	public int getSize() {
		return size;
	}

	protected void remove(Node node) {
		if (node == head)
			head = node.next;
		if (node == tail)
			tail = node.prev;
		if (node.next != null)
			node.next.prev = node.prev;
		if (node.prev != null)
			node.prev.next = node.next;
		size--;
	}
	
	public Node remove(T data, Comparator<T> comparator) {
		if (head == null)
			return null;
		Node current_node = head;
		while (current_node != null) {
			if (comparator.compare(current_node.data, data) == 0) {
				remove(current_node);
				break;
			}
			current_node = current_node.next;
		}
		return current_node;
	}

	public T retrieveFirstElement() {
		T temp = head.data;
		remove(head);
		return temp;
	}

	public T retrieveLastElement() {
		T temp = tail.data;
		remove(tail);
		return temp;
	}

	public ArrayList<T> toArrayList() {
		ArrayList<T> array = new ArrayList<T>();
		Node current_node = head;
		while (current_node != null) {
			array.add(current_node.data);
			current_node = current_node.next;
		}
		return array;
	}

	@Override
	public ListIterator<T> iterator() {
		return new DoubleLinkedListIterator();
	}

	protected class Node {

		public Node prev, next;
		public T data;

		public Node(T data) {
			this.data = data;
		}

	}

	protected class DoubleLinkedListIterator implements ListIterator<T> {

		Node previous;
		Node current;
		Node next;
		
		public DoubleLinkedListIterator() {
			current = head;
			if (head != null) {
				previous = head.prev;
				next = head.next;
			}
		}
		
		@Override
		public T next() {
			if (!hasNext())
				throw new NoSuchElementException();
			previous = current;
			current = next;
			next = (current != null) ? current.next : null;

			return previous.data; // The cursor jumps over the NOW previous item
		}

		@Override
		public T previous() {
			if (!hasPrevious())
				throw new NoSuchElementException();
			next = current;
			current = previous;
			previous = (current != null) ? current.prev : null;
			
			return current.data; // The cursor jumps backwards over the NOW current item
		}

		@Override
		public boolean hasNext() {
			return next != null || (next == null && current != null);
		}

		@Override
		public boolean hasPrevious() {
			return previous != null;
		}

		@Override
		public void add(T arg0) {
			throw new UnsupportedOperationException();
		}

		@Override
		public int nextIndex() {
			throw new UnsupportedOperationException();
		}

		@Override
		public int previousIndex() {
			throw new UnsupportedOperationException();
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

		@Override
		public void set(T arg0) {
			throw new UnsupportedOperationException();
		}

	}



}
