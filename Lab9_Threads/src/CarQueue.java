import java.util.Random;

public class CarQueue {

	private DirectionNode head;
	private DirectionNode tail;
	private Random random;
	
	private class DirectionNode {
		private DirectionNode next;
		private int data;
		
		public DirectionNode(int data) {
			this.data = data;
		}
	}

	public CarQueue() {
		random = new Random();
		add(random.nextInt(0, 4));
		add(random.nextInt(0, 4));
		add(random.nextInt(0, 4));
		add(random.nextInt(0, 4));
		add(random.nextInt(0, 4));
		add(random.nextInt(0, 4));
		printQueue();
	}
	
	public boolean isEmpty() {
		return head == null;
	}

	private void add(int data) {
		if (head == null) {
			head = new DirectionNode(data);
			System.out.println("new head " + head.data);
			tail = head;
			return;
		}
		
		if (tail == null)
			tail = head;
		tail.next = new DirectionNode(data);
		tail = tail.next;
	}
	
	public void addToQueue() {
		class AddToQueue implements Runnable{

			@Override
			public void run() {
				for(int i = 0; i < 20; i++) {
					add(random.nextInt(0, 4));
					printQueue();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			
		}
		Thread thread = new Thread(new AddToQueue());
		thread.start();
	}

	public void printQueue() {
		System.out.println(toString());
	}
	
	@Override
	public String toString() {
		String result = "";
		if (head == null)
			return result;
		DirectionNode current = head;
		while (current.next != null) {
			current = current.next;
			result += current.data + " ";
		}
		return result;
	}
	
	public int deleteQueue() {
		DirectionNode old_head = head;
		if (head == null)
			return -1;
		head = head.next;
		if (head == null)
			tail = null;
		return old_head.data;
	}
	
	
	
}
