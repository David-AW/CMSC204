import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;

public class BasicDoubleLinkedList<T> implements Iterable<T>{

	protected Node head;
	protected Node tail;
	protected int size = 0;

	public void addToFront(T data) {
		
	}
	
	public void addToEnd(T data) {

	}
	
	public T getFirst() {
		return null;
	}

	public T getLast() {
		return null;
	}

	public int getSize() {
		return size;
	}

	public Node remove(T data, Comparator<T> comparator) {
		return null;
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
