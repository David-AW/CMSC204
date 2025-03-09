import java.util.Comparator;

public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T>{

	public SortedDoubleLinkedList(Comparator<T> comparator) {
		
	}

	public void add(T data) {
		
	}
	
	/**
	 * This method is not implemented for this Data Type
	 * @throws UnsupportedOperationException when this method is executed
	 */
	@Override
	public void addToFront(T data) {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * This method is not implemented for this Data Type
	 * @throws UnsupportedOperationException when this method is executed
	 */
	@Override
	public void addToEnd(T data) {
		throw new UnsupportedOperationException();
	}
	
}
