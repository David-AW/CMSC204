import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;

public class BasicDoubleLinkedList<T> implements Iterable<T>{

	protected Node head;
	protected Node tail;
	protected int size = 0;

	public void addToFront(T data) {
		Node temp = new Node(data);
		temp.next = head;
		head.prev = temp;
		head = temp;
		size++;
	}
	
	public void addToEnd(T data) {
		Node temp = new Node(data);
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

	public Node remove(T data, Comparator<T> comparator) {
		if (head == null)
			return null;
		Node current_node = head;
		while (current_node != null) {
			if (comparator.compare(current_node.data, data) == 0) {
				if (current_node == head)
					head = current_node.next;
				if (current_node == tail)
					tail = current_node.prev;
				if (current_node.next != null)
					current_node.next.prev = current_node.prev;
				if (current_node.prev != null)
					current_node.prev.next = current_node.next;
				break;
			}
			current_node = current_node.next;
		}
		return current_node;
	}

	public T retrieveFirstElement() {
		return null;
	}

	public T retrieveLastElement() {
		return null;
	}

	public ArrayList<T> toArrayList() {
		return null;
	}

	@Override
	public ListIterator<T> iterator() {
		return new DoubleLinkedListIterator<T>();
	}

	protected class Node {

		public Node prev, next;
		public T data;

		public Node(T data) {
			this.data = data;
		}

	}

	protected class DoubleLinkedListIterator<T> implements ListIterator<T> {

		@Override
		public T next() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public T previous() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean hasPrevious() {
			// TODO Auto-generated method stub
			return false;
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
