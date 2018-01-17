package com.concurrent_core.semaphore;

public class MyThread2Pool extends Thread{

	private ListPool pool;

	public MyThread2Pool(ListPool pool) {
		super();
		this.pool = pool;
	}

	@Override
	public void run() {
		for(int i=0;i<Integer.MAX_VALUE;i++){
			String str = pool.get();
			System.out.println(Thread.currentThread().getName()+" 取得值 "+str);
			pool.put(str);
		}
	}
	
	public static void main(String[] args) {
		ListPool pool = new ListPool();
		
		MyThread2Pool[] thread2Pool = new MyThread2Pool[12];
		for(int i=0;i<thread2Pool.length;i++){
			thread2Pool[i] = new MyThread2Pool(pool);
		}
		
		for(int i=0;i<thread2Pool.length;i++){
			thread2Pool[i].start();;
		}
	}
	
}
