import java.util.ArrayList;

public class MyQueue<T> implements QueueInterface<T>{

	Object[] storage;
	
	public MyQueue() {
		this(16);
	}
	
	public MyQueue(int queue_size) {
		storage = new Object[queue_size];
	}
	
	@Override
	public boolean enqueue(T e) throws QueueOverflowException {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public T dequeue() throws QueueUnderflowException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void fill(ArrayList<T> list) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString(String delimiter) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
