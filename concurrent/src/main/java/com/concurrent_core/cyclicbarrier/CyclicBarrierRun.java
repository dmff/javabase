package com.concurrent_core.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 实现阶段性的跑步比赛
 * @author dmf
 *
 */
public class CyclicBarrierRun {

	private CyclicBarrier barrier;

	public CyclicBarrierRun(CyclicBarrier barrier) {
		super();
		this.barrier = barrier;
	}
	
	
	public void run(){
		
		try {
			long mills = (int)(Math.random()*10000);
			Thread.sleep(mills);
			
			System.out.println(Thread.currentThread().getName()+":"+
					System.currentTimeMillis()+" begin跑第一阶段"+(barrier.getNumberWaiting()+1));
			
			barrier.await();
			
			System.out.println(Thread.currentThread().getName()+":"+
					System.currentTimeMillis()+" end跑第一阶段"+barrier.getNumberWaiting());
			
			Thread.sleep(mills);
			
			System.out.println(Thread.currentThread().getName()+":"+
					System.currentTimeMillis()+" begin跑第二阶段"+(barrier.getNumberWaiting()+1));
			
			barrier.await();
			
			System.out.println(Thread.currentThread().getName()+":"+
					System.currentTimeMillis()+" end跑第二阶段"+barrier.getNumberWaiting());
		
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		CyclicBarrier barrier = new CyclicBarrier(2);
		CyclicBarrierRun run = new CyclicBarrierRun(barrier);
		
		Thread thread1 = new Thread(run::run,"threadA");
		thread1.start();
		
		Thread thread2 = new Thread(run::run,"threadB");
		thread2.start();
		
		Thread thread3 = new Thread(run::run,"threadC");
		thread3.start();
		
		Thread thread4 = new Thread(run::run,"threadD");
		thread4.start();
	}
}
