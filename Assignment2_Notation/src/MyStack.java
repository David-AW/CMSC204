import java.util.ArrayList;

public class MyStack<T> implements StackInterface<T>{

	Object[] storage;
	int next = 0;
	
	public MyStack() {
		this(16);
	}
	
	public MyStack(int stack_size) {
		storage = new Object[stack_size];
	}
	
	@Override
	public boolean push(T e) throws StackOverflowException {
		if (isFull())
			throw new StackOverflowException();
		storage[next++] = e;
		return true;
	}
	
	@Override
	public T pop() throws StackUnderflowException {
		if (isEmpty())
			throw new StackUnderflowException();
		return (T) storage[--next];
	}
	
	@Override
	public void fill(ArrayList<T> list) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean isEmpty() {
		return next <= 0;
	}

	@Override
	public boolean isFull() {
		return next >= storage.length;
	}

	@Override
	public T top() throws StackUnderflowException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		return next;
	}

	@Override
	public String toString(String delimiter) {
		// TODO Auto-generated method stub
		return null;
	}

}
