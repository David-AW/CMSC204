import java.util.ArrayList;

public class MyQueue<T> implements QueueInterface<T>{

	Object[] storage;
	
	int start = 0;
	int next = 0;
	
	public MyQueue() {
		this(16);
	}
	
	public MyQueue(int queue_size) {
		storage = new Object[queue_size];
	}
	
	@Override
	public boolean enqueue(T e) throws QueueOverflowException {
		if (isFull())
			throw new QueueOverflowException();
		storage[next++ % storage.length] = e;
		return true;
	}
	
	@Override
	public T dequeue() throws QueueUnderflowException {
		if (isEmpty())
			throw new QueueUnderflowException();
		T temp = (T) storage[start++];
		// This is to avoid the inevitable possibility of running out of bits in the integer
		if (start >= storage.length) {
			next -= start;
			start = 0;
		}
		return temp;
	}
	
	@Override
	public void fill(ArrayList<T> list) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean isEmpty() {
		return start == next;
	}

	@Override
	public boolean isFull() {
		return size() >= storage.length;
	}

	@Override
	public int size() {
		return next-start;
	}

	@Override
	public String toString(String delimiter) {
		String temp = "";
		for (int i = start; i < next; i++) {
			temp += storage[i%storage.length].toString();
			if (i < next-1)
				temp += delimiter;
		}
		return temp;
	}
	
	@Override
	public String toString() {
		return toString("");
	}
	
}
