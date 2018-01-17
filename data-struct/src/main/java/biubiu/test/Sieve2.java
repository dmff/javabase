package biubiu.test;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class Sieve2 {

	private static CopyOnWriteArraySet<Integer> nums = new CopyOnWriteArraySet<>();
	
	static{
		for(int i=1;i<10000000;i++){
			nums.add(i);
		}
	}
	
	public static void main(String[] args) throws IOException {
		ForkJoinPool pool = new ForkJoinPool();
		Task task = new Task(0, 10000000);
		long start = System.currentTimeMillis();
		pool.execute(task);
		long end = System.currentTimeMillis();
		System.out.println(end-start);
		System.in.read();
	}
	
	static class Task extends RecursiveAction{

		int start;
		int end;
		
		public Task(int start, int end) {
			super();
			this.start = start;
			this.end = end;
		}

		@Override
		protected void compute() {
			if (end-start<50000) {
				//处理方法
			}else {
				int mid = start +(end-start)/2;
				Task task1 = new Task(start, mid);
				Task task2 = new Task(mid+1, end);
				task1.fork();
				task2.fork();
			}
			
		}
		
	}
}
