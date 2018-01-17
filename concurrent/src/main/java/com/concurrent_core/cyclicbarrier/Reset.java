package com.concurrent_core.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 重置时，在屏障点等待的县城会抛出broken异常
 * @author dmf
 *
 */
public class Reset {

	CyclicBarrier barrier = new CyclicBarrier(3, new Runnable() {
		
		@Override
		public void run() {
			System.out.println("   结束了  "+System.currentTimeMillis());
		}
	});
	
	public void test(){
		try {
			System.out.println(Thread.currentThread().getName()+" 准备! "+System.currentTimeMillis());
			barrier.await();
			System.out.println(Thread.currentThread().getName()+" 结束! "+System.currentTimeMillis());
		} catch (InterruptedException | BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) throws InterruptedException {
		Reset reset = new Reset();
		
		Thread thread1 = new Thread(reset::test, "threadA");
		thread1.start();
		
		Thread thread2 = new Thread(reset::test, "threadB");
		thread2.start();
		
		Thread.sleep(2000);
		reset.barrier.reset();
	}
}

