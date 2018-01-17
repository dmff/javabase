package biubiu.array;

/**
 * 基本队列的功能
 * @author dmf
 *
 */
public class MyQueue {

	private static int DEFAULT_SIZE=10;
	
	private int[] queue;
	private int rear;
	private int front;
	private int maxsize;
	
	public MyQueue(int size) {
		super();
		maxsize = size;
		queue = new int[size];
		rear=0;
		front=0;
	}

	public MyQueue() {
		this(DEFAULT_SIZE);
	}
	
	public int size(){
		return rear-front;
	}
	
	public boolean isEmpty(){
		return rear==front? true :false;
	}
	
	public void insert(int value){
		if(rear == queue.length){
			//扩容操作
			throw new RuntimeException("超出队列的大小");
		}
		queue[rear++] = value;
	}
	
	public int peek(){
		if (isEmpty()) {
			throw new RuntimeException("队列为null");
		}
		return queue[front];
	}
	
	public int poll(){
		if (isEmpty()) {
			throw new RuntimeException("队列为null");
		}
		int value = queue[front];
		queue[front++] = Integer.MIN_VALUE;
		return value;
	}
	
	public static void main(String[] args) {
		MyQueue queue = new MyQueue(3);
		queue.insert(1);
		queue.insert(2);
		
		//queue.insert(3);
		//queue.insert(4);
		System.out.println(queue.size());
		System.out.println(queue.peek());
		System.out.println(queue.poll());
		System.out.println(queue.poll());
		System.out.println(queue.poll());
	}
	
	
	
}
