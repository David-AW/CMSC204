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
		for (T item : list) {
			if (isFull())
				break;
			push(item);
		}
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
		if (isEmpty())
			throw new StackUnderflowException();
		return (T) storage[next-1];
	}

	@Override
	public int size() {
		return next;
	}

	@Override
	public String toString(String delimiter) {
		String temp = "";
		for (int i = 0; i < next; i++) {
			temp += storage[i].toString();
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
