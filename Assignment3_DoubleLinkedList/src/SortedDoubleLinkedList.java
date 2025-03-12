import java.util.Comparator;

public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T>{

	Comparator<T> comparator;
	
	public SortedDoubleLinkedList(Comparator<T> comparator) {
		this.comparator = comparator;
	}

	/**
	 * Adds element to the list in the position sorted according to the assigned comparator and updated the size of the list
	 * @param data Data to add to the list
	 */	
	public void add(T data) {
		Node temp = new Node(data);
		size++;
		if (head == null) {
			head = temp;
			tail = head;
			return;
		}
		
		Node current_node = head;
		while(current_node != null) {
			if (comparator.compare(current_node.data, data) >= 0) {
				if (current_node.prev == null) { // This is true only if the item is less than the head node, so we set this item to the new head
					head = temp;
					temp.next = current_node;
					current_node.prev = temp;
				}else { // Splice this item into the double linked list at the position BEFORE the current node which is larger in comparison
					temp.prev = current_node.prev;
					current_node.prev.next = temp;
					current_node.prev = temp;
					temp.next = current_node;
				}
				return; // We found a spot for this item so exit this method
			}
			current_node = current_node.next;
		}
		
		// If we reached the end of the linked list, then go to the tail node and add item as the new tail
		if (current_node == null)
			current_node = tail;
		current_node.next = temp;
		temp.prev = current_node;
		tail = temp;
	}
	
	@Override
	public Node remove(T data, Comparator<T> comparator) {
		if (head == null)
			return null;
		Node current_node = head;
		int comparison;
		while (current_node != null) {
			comparison = comparator.compare(current_node.data, data);
			if (comparison == 0) {
				remove(current_node);
				break;
			}else if (comparison > 0) {
				return null; // return null because the item does not exist if there is only larger items left in a sorted list
			}
			current_node = current_node.next;
		}
		return current_node;
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
